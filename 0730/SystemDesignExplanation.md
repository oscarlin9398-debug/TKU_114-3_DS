# 系統設計與資料結構選擇說明文件 (System Design Explanation)

**專案名稱**：活動報名與候補系統 (Event Registration System)  
**文件檔名**：`SystemDesignExplanation.md`  
**對應程式檔**：
1. `Registration.java`
2. `RegistrationAlgorithms.java`
3. `EventRegistrationSystem.java`

---

## 概述
本文件針對「活動報名與候補系統」選出之 **6 個核心功能** 進行資料結構與演算法的設計分析。內容明確列出對應的 **程式檔名**、**Method 名稱**、**採用的資料結構/演算法**，並與替代方案進行優缺點及複雜度分析，說明選型原因。

---

## 核心功能與資料結構選型分析

### 機能 1：儲存與維護全體報名紀錄 (Master Record Storage)
* **對應檔名**：`EventRegistrationSystem.java`
* **對應 Method**：`register()` / `sortAndDisplayById()`
* **採用技術**：`ArrayList<Registration>` (`allRegistrations`)

#### 選擇原因與優勢
1. **動態擴充與隨機存取**：報名人數為動態成長，`ArrayList` 具備動態擴容特性。且其底層為連續記憶體陣列，支援 $O(1)$ 的隨機存取（Random Access），有利於後續透過索引直接抓取元素與排序。
2. **快取友善性 (Cache Locality)**：記憶體連續分布使得 CPU Cache Hit Rate 高，在進行全資料遍歷時效能顯著高於鏈結串列。

#### 未採用「`LinkedList`」的原因
`LinkedList` 的節點（Node）在記憶體中為分散配置，每次讀取皆需透過指標尋址（$O(N)$ 隨機存取），導致 Cache Miss 率高。此外，對 `LinkedList` 進行 Merge Sort 或 Binary Search 時，取得特定索引元素的開銷過大，不適合做為排序與搜尋的主資料庫。

---

### 機能 2：額滿人員排隊與自動遞補 (Waiting Line & Nomination)
* **對應檔名**：`EventRegistrationSystem.java`
* **對應 Method**：`register()` / `cancelRegistration()`
* **採用技術**：`Queue<Registration>` (`waitingQueue` / `ArrayDeque`)

#### 選擇原因與優勢
1. **嚴格符合先來先服務 (FIFO - First In, First Out)**：候補系統的核心精神為「公平性」，先額滿轉候補者必須優先遞補。`Queue` 的 `offer()`（入列）與 `poll()`（出列）完美的契合此邏輯，操作時間複雜度皆為 $O(1)$。
2. **避免誤操作**：`Queue` 的介面限制僅能從隊尾進、隊頭出，能從結構層面杜絕隨意插隊或不正當遞補的程式邏輯漏洞。

#### 未採用「`ArrayList`（以索引 0 做為隊頭）」的原因
若使用 `ArrayList` 模擬佇列，當隊頭人員遞補（移除 Index 0）時，後方所有元素皆需向前平移一個位置，時間複雜度高達 $O(N)$。當候補人數龐大時會產生嚴重的效能瓶頸；而 `ArrayDeque` 實作的 `Queue` 移出隊頭僅需 $O(1)$。

---

### 機能 3：最近取消紀錄備份與復原 (Cancellation History & Undo)
* **對應檔名**：`EventRegistrationSystem.java`
* **對應 Method**：`cancelRegistration()` / `undoLastCancellation()`
* **採用技術**：`Stack` (`Deque<Registration>` / `cancelledStack`)

#### 選擇原因與優勢
1. **符合後進先出 (LIFO - Last In, First Out)**：復原（Undo）功能必然要從「最近一次發生的動作」開始倒回。`Stack` 的 `push()` 與 `pop()` 操作時間複雜度為 $O(1)$，完美匹配操作復原語意。
2. **狀態明確**：將取消的資料獨立於 Stack 中，不污染正取與候補佇列，直到使用者觸發復原時才重新進行狀態分發。

#### 未採用「普通 `ArrayList` 或 `Queue`」的原因
* 若採用 `Queue`（FIFO），復原時會變成「最早取消的人先被復原」，完全違反常理的 Undo 邏輯。
* 若採用 `ArrayList`，雖然可以從尾端取出，但缺乏 Stack 結構特有的語意約束，容易在協同開發時誤用中段索引刪除，破壞復原鏈的完整性。

---

### 機能 4：依報名編號進行穩定排序 (Order Registration Records)
* **對應檔名**：`RegistrationAlgorithms.java`
* **對應 Method**：`sortByIdAscending()`
* **採用技術**：`Merge Sort` (合併排序法)

#### 選擇原因與優勢
1. **穩定時間複雜度**：Merge Sort 採用分治法（Divide and Conquer），無論輸入資料是完全亂序、已排序或反向排序，其時間複雜度恆定為 $O(N \log N)$，不會產生極端惡化的狀況。
2. **排序穩定性 (Stability)**：Merge Sort 為穩定排序（Stable Sort），在報名編號相同的特殊邊界情況下，能保持資料原本的相對順序，確保報名時間先後的公平性。

#### 未採用「`Quick Sort` (快速排序法)」的原因
Quick Sort 在最壞情況下（如 pivot 選擇不佳且資料已排序時），時間複雜度會嚴重退化至 $O(N^2)$。對於正式線上系統而言，$O(N^2)$ 的潛在風險不可接受，因此選擇效能更為穩健的 Merge Sort。

---

### 機能 5：依報名編號精確快速查詢 (Find Record by Registration ID)
* **對應檔名**：`RegistrationAlgorithms.java`
* **對應 Method**：`binarySearchById()`
* **採用技術**：`Binary Search` (二分搜尋法)

#### 選擇原因與優勢
1. **極致搜尋效能**：報名編號（`regId`）為系統唯一鍵（Unique Key）。經由 Merge Sort 排序後，Binary Search 每次比較皆能砍掉一半的搜尋範圍，時間複雜度僅為 $O(\log N)$。
2. **規模擴充性高**：當報名筆數達到 1,000,000 筆時，Binary Search 最多僅需要約 20 次比較即可定位目標，效能極高。

#### 未採用「`Sequential Search` (循序搜尋法)」的原因
Sequential Search 的時間複雜度為 $O(N)$。在資料量龐大時，若查詢目標位於陣列末端或不存在，必須遍歷全體資料，在頻繁查詢編號的場景下會造成不必要的 CPU 資源浪費。

---

### 機能 6：依姓名模糊或重複查詢 (Find Records by Customer Name)
* **對應檔名**：`RegistrationAlgorithms.java`
* **對應 Method**：`sequentialSearchByName()`
* **採用技術**：`Sequential Search` (循序搜尋法)

#### 選擇原因與優勢
1. **支援同名同姓與未排序欄位**：報名系統主庫是依「報名編號」排序，而非「姓名」。在姓名欄位未排序且允許同名同姓（一對多結果）的情景下，必須完整走訪整個清單才能找齊所有符合條件的紀錄。
2. **實作簡單且彈性**：搜尋時間複雜度為 $O(N)$，符合對未排序、非唯一鍵欄位進行全表掃描的客觀演算法限制。

#### 未採用「`Binary Search` (二分搜尋法)」的原因
Binary Search 的**絕對前提**是「資料必須針對搜尋鍵進行排序」。若要對姓名使用 Binary Search，必須先將整個 `ArrayList` 依姓名重新排序，這會破壞原本已依編號排好的結構，且重新排序的成本 $O(N \log N)$ 遠高於一次性循序搜尋的 $O(N)$。

---

## 📊 綜合演算法與資料結構比較表

| 比較項目 / 技術名稱 | 結構/類別 | 最佳時間複雜度 | 平均時間複雜度 | 最壞時間複雜度 | 空間複雜度 | 適用場景與限制 |
| :--- | :--- | :---: | :---: | :---: | :---: | :--- |
| **ArrayList** | 動態陣列 | $O(1)$ (隨機存取) | $O(1)$ (讀取) | $O(N)$ (插入/刪除) | $O(N)$ | 適合頻繁讀取、尾端新增，不適合中段頻繁插拔。 |
| **Queue** | 先進先出 (FIFO) | $O(1)$ | $O(1)$ | $O(1)$ | $O(N)$ | 適合排隊、候補、任務發送等具先後順序之情境。 |
| **Stack** | 後進先出 (LIFO) | $O(1)$ | $O(1)$ | $O(1)$ | $O(N)$ | 適合 Undo/Redo、遞迴呼叫堆疊、括號比對。 |
| **Merge Sort** | 分治排序法 | $O(N \log N)$ | $O(N \log N)$ | $O(N \log N)$ | $O(N)$ | 效能極度穩定且具備穩定性，但需額外記憶體空間。 |
| **Binary Search** | 折半搜尋法 | $O(1)$ | $O(\log N)$ | $O(\log N)$ | $O(1)$ | **必須先經排序**，適合 key 值的快速精確定位。 |
| **Sequential Search**| 循序搜尋法 | $O(1)$ | $O(N)$ | $O(N)$ | $O(1)$ | 適用於未排序資料、非唯一鍵、多筆結果收集。 |

---

## 總結
本系統綜合評估了資料的**存取特性**、**排序穩定度**與**情境語意**：
* 選擇 `ArrayList` 作為主要儲存載體以發揮 $O(1)$ 隨機存取優勢。
* 搭配 `Queue` 與 `Stack` 精準模擬「候補排隊」與「取消復原」的業務邏輯。
* 在檢索層面，依據資料是否排序及是否唯一，分別選用 $O(\log N)$ 的 `Binary Search` 與 $O(N)$ 的 `Sequential Search`，達成效能與功能性的完整平衡。