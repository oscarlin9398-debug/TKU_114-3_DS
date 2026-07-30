# 完整測試紀錄報告 (TestCases.md)

**專案名稱**：活動報名與候補系統 (Event Registration System)  
**文件檔名**：`TestCases.md`  
**測試日期**：2026-07-30  
**測試環境**：JDK 17 / macOS  

---

## 摘要說明
本測試文件針對「活動報名與候補系統」進行涵蓋性測試，包含 **空資料**、**單筆**、**重複**、**邊界值（上限額滿/候補轉遞補）**、**查無資料** 及 **Stack 復原（Undo）** 等情境。

* **測試總案例數**：15
* **初測通過**：14
* **初測未通過**：1 (修正後複測通過)
* **最終狀態**：ALL PASSED (100% 通過)

---

## 🧪 測試案例列表

### 分類 1：邊界情境與初始狀態 (空資料/邊界測試)

#### Test Case 1: 系統初始空狀態執行取消操作
* **案例類型**：空資料 / 邊界測試
* **前置條件**：系統剛啟動，`allRegistrations` 為空，`cancelledStack` 為空。
* **輸入資料**：`regId` = `"REG000"`
* **操作步驟**：呼叫 `system.cancelRegistration("REG000")`
* **預期結果**：系統應捕捉並顯示「查無報名編號」訊息，不應拋出例外（NullPointerException 或 Exception）。
* **實際結果**：顯示 `[取消失敗] 查無報名編號 [REG000] 的紀錄`。
* **測試狀態**：✅ 通過 (PASS)

---

#### Test Case 2: 空復原堆疊（Stack is empty）執行 Undo
* **案例類型**：空資料 / Stack 邊界
* **前置條件**：`cancelledStack` 目前筆數為 0。
* **操作步驟**：呼叫 `system.undoLastCancellation()`
* **預期結果**：系統印出提示訊息表明無紀錄可復原，不影響系統正常運行。
* **實際結果**：顯示 `[復原失敗] 沒有任何取消紀錄可供復原 (Stack is empty)`。
* **測試狀態**：✅ 通過 (PASS)

---

#### Test Case 3: 空資料庫執行 Merge Sort 與排序顯示
* **案例類型**：空資料 / 演算法邊界
* **前置條件**：`allRegistrations` 無任何資料。
* **操作步驟**：呼叫 `system.sortAndDisplayById()`
* **預期結果**：輸出提示訊息「目前無任何報名資料」，且 Merge Sort 遞迴界線條件 (`left >= right`) 應安全返回，無陣列越界。
* **實際結果**：顯示 `目前無任何報名資料`。
* **測試狀態**：✅ 通過 (PASS)

---

### 分類 2：單筆與正常註冊情境

#### Test Case 4: 單筆資料成功報名 (取得正取)
* **案例類型**：單筆測試
* **前置條件**：系統上限為 3 人，目前正取 0 人。
* **輸入資料**：`regId` = `"REG804"`, `name` = `"Alice"`, `phone` = `"0912-345678"`
* **操作步驟**：呼叫 `system.register("REG804", "Alice", "0912-345678")`
* **預期結果**：狀態設為「正取」，正取人數變為 1/3，系統顯示報名成功。
* **實際結果**：顯示 `[報名成功] [REG804 - Alice] 已成功取得正取名額 (正取: 1/3)`。
* **測試狀態**：✅ 通過 (PASS)

---

#### Test Case 5: 正取名額到達邊界上限 (容量點對齊)
* **案例類型**：邊界測試
* **前置條件**：系統上限為 3 人，已有 2 人正取。
* **輸入資料**：`regId` = `"REG930"`, `name` = `"Charlie"`, `phone` = `"0934-567890"`
* **操作步驟**：呼叫 `system.register("REG930", "Charlie", "0934-567890")`
* **預期結果**：取得最後一個正取名額 (正取: 3/3)，狀態為「正取」。
* **實際結果**：顯示 `[報名成功] [REG930 - Charlie] 已成功取得正取名額 (正取: 3/3)`。
* **測試狀態**：✅ 通過 (PASS)

---

### 分類 3：重複資料與 Queue 候補機制

#### Test Case 6: 重複報名編號 (Duplicate Registration ID)
* **案例類型**：重複資料
* **前置條件**：編號 `"REG105"` 已存在於清單中。
* **輸入資料**：`regId` = `"REG105"`, `name` = `"DuplicateBob"`, `phone` = `"0999-000000"`
* **操作步驟**：呼叫 `system.register("REG105", "DuplicateBob", "0999-000000")`
* **預期結果**：系統應透過 HashSet 比對攔截，拒絕重複註冊並回傳 `false`。
* **實際結果**：顯示 `[系統警告] 報名失敗！報名編號 REG105 已存在，重複編號不可註冊`。
* **測試狀態**：✅ 通過 (PASS)

---

#### Test Case 7: 正取額滿，自動轉入候補佇列 (Queue Push)
* **案例類型**：邊界測試 / Queue 功能
* **前置條件**：正取已滿 (3/3)，`waitingQueue` 為空。
* **輸入資料**：`regId` = `"REG212"`, `name` = `"David"`, `phone` = `"0945-678901"`
* **操作步驟**：呼叫 `system.register("REG212", "David", "0945-678901")`
* **預期結果**：報名狀態設為「候補中」，並進入 `waitingQueue`（候補順序第 1 位）。
* **實際結果**：顯示 `[額滿轉候補] 正取已滿 (3/3)，[REG212 - David] 已進入候補佇列 (候補順序: 第 1 位)`。
* **測試狀態**：✅ 通過 (PASS)

---

### 分類 4：搜尋演算法 (Binary Search & Sequential Search)

#### Test Case 8: 二分搜尋精確查找已有編號 (Binary Search - Hit)
* **案例類型**：搜尋測試
* **前置條件**：清單包含多筆資料，且已執行 Merge Sort。
* **輸入資料**：`targetId` = `"REG105"`
* **操作步驟**：呼叫 `system.searchByIdBinary("REG105")`
* **預期結果**：成功回傳該物件於陣列中之索引（例如 Index 0），並正確印出該筆資料。
* **實際結果**：顯示 `[搜尋成功] 位於索引位置 0：報名編號: REG105 | 姓名: Bob`。
* **測試狀態**：✅ 通過 (PASS)

---

#### Test Case 9: 二分搜尋查無此編號 (Binary Search - Miss)
* **案例類型**：找不到資料
* **前置條件**：清單已有資料，但未含有 `"REG999"`。
* **輸入資料**：`targetId` = `"REG999"`
* **操作步驟**：呼叫 `system.searchByIdBinary("REG999")`
* **預期結果**：演算法回傳 `-1`，系統輸出「查無報名編號」訊息。
* **實際結果**：顯示 `[搜尋失敗] 查無報名編號 [REG999] 的資料`。
* **測試狀態**：✅ 通過 (PASS)

---

#### Test Case 10: 循序搜尋符合姓名 (Sequential Search - Hit)
* **案例類型**：搜尋測試
* **前置條件**：清單中存在姓名為 `"David"` 的成員。
* **輸入資料**：`name` = `"David"`
* **操作步驟**：呼叫 `system.searchByNameSequential("David")`
* **預期結果**：成功找到並列出符合特徵的 1 筆紀錄。
* **實際結果**：顯示 `找到 1 筆符合姓名的報名紀錄： 報名編號: REG212 | 姓名: David`。
* **測試狀態**：✅ 通過 (PASS)

---

#### Test Case 11: 循序搜尋查無姓名 (Sequential Search - Miss)
* **案例類型**：找不到資料
* **前置條件**：清單中不存在姓名為 `"Frank"` 的紀錄。
* **輸入資料**：`name` = `"Frank"`
* **操作步驟**：呼叫 `system.searchByNameSequential("Frank")`
* **預期結果**：回傳空 List，系統提示「查無姓名」訊息。
* **實際結果**：顯示 `[搜尋結果] 查無姓名為 [Frank] 的任何報名紀錄`。
* **測試狀態**：✅ 通過 (PASS)

---

### 分類 5：取消、遞補與 Stack 復原（Undo）

#### Test Case 12: 取消正取，自動引發 Queue 頭端候補遞補 (Poll Queue)
* **案例類型**：邊界 / Queue 遞補
* **前置條件**：正取 3 人滿，候補佇列有 David（頭位）與 Eve。
* **輸入資料**：`regId` = `"REG804"` (Alice - 正取)
* **操作步驟**：呼叫 `system.cancelRegistration("REG804")`
* **預期結果**：Alice 狀態變更為「已取消」並 push 至 Stack；出缺的名額由佇列頭端的 David 自動遞補，David 狀態改為「正取」。
* **實際結果**：顯示 `[辦理成功] ... 已成功辦理取消並存入復原堆疊` 與 `[候補遞補] 候補人員 [REG212 - David] 已自動遞補為正取名額！`。
* **測試狀態**：✅ 通過 (PASS)

---

#### Test Case 13: 復原最新取消者 (Stack Pop Undo) - 正取已滿情景
* **案例類型**：復原操作 / Stack 機能
* **前置條件**：剛才取消了 Alice，但名額已被 David 遞補填滿 (3/3)。
* **操作步驟**：呼叫 `system.undoLastCancellation()`
* **預期結果**：從 Stack 弹出（Pop）Alice；因正取已滿，Alice 應自動回歸「候補佇列」末端，狀態更新為「候補中」。
* **實際結果**：顯示 `[復原成功] 正取已滿，[REG804 - Alice] 已重新加入【候補佇列】末端`。
* **測試狀態**：✅ 通過 (PASS)

---

#### Test Case 14: 取消候補中人員 (Cancel Waitlist Member)
* **案例類型**：Queue 中段移出
* **前置條件**：Eve (`REG501`) 處於「候補中」狀態。
* **輸入資料**：`regId` = `"REG501"`
* **操作步驟**：呼叫 `system.cancelRegistration("REG501")`
* **預期結果**：Eve 狀態更新為「已取消」，並自候補佇列移出，不觸發正取遞補邏輯。
* **實際結果**：顯示 `[佇列調整] [REG501] 自候補佇列中移出，目前候補人數: 1 位`。
* **測試狀態**：✅ 通過 (PASS)

---

#### Test Case 15: 二次重複取消已取消的紀錄 (Repeat Cancellation Protection)
* **案例類型**：重複/重複操作保護
* **前置條件**：`REG804` 在先前測試中已被辦理取消，且尚未復原。
* **輸入資料**：`regId` = `"REG804"`
* **操作步驟**：呼叫 `system.cancelRegistration("REG804")`
* **預期結果**（修訂後）：系統辨識出該紀錄狀態為「已取消」，拒絕重複辦理取消與重複 Push 入 Stack。
* **初測結果**：❌ 未通過 (FAIL) — 初測時系統未判斷 `getStatus().equals("已取消")`，導致同一個人被重複推入 `cancelledStack`，破壞統計數字與 Undo 鏈。
* **修正內容**：在 `EventRegistrationSystem.java` 的 `cancelRegistration` 方法中增加狀態檢查判斷式：
  ```java
  if (target.getStatus().equals("已取消")) {
      System.out.printf("  [取消失敗] 報名編號 [%s] 先前已辦理過取消。%n", regId);
      return;
  }