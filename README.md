Hello mobile-class-aggregator

# 1.thymeleaf 调用javascript方法
```
'javascript:openBox(\''+${curCabNo}+'\',\''+${box.no}+'\')'
```
---
# 2. jQuery获取radio-group的值
```
<div class="btn-group" id="color" data-toggle="buttons">
       <label class="btn btn-default">
         <input type="radio" class="toggle" value="1"> 红色
       </label>
       <label class="btn btn-default">
         <input type="radio" class="toggle" value="2"> 绿色
       </label>
       <label class="btn btn-default">
         <input type="radio" class="toggle" value="3"> 蓝色
       </label>
</div>

var selectedColor = $('#color input:radio:checked').val();
```