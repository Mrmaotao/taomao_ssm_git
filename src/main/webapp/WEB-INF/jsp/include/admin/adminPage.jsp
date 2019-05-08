<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<script>
$(function(){
	$("ul.pagination li.disabled a").click(function(){
		return false;
	});
});

</script>

<nav>
  <ul class="pagination">
    <li <c:if test="${!page.isFront()}">class="disabled"</c:if>>
    <!-- 首页超链 -->
      <a  href="?start=0" aria-label="Previous" >
        <span aria-hidden="true">«</span>
      </a>
    </li>

    <li <c:if test="${!page.isFront()}">class="disabled"</c:if>>
    <!--上一页超链  -->
      <a  href="?start=${page.start-page.count}" aria-label="Previous" >
        <span aria-hidden="true">‹</span>
      </a>
    </li>    
     <!--中间页 -->
    <c:forEach begin="0" end="${page.getTotalPage()}" varStatus="status">
    		    <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
		    	<a
		    	href="?start=${status.index*page.count}"
		    	<c:if test="${status.index*page.count==page.start}">class="current"</c:if>
		    	>${status.count}</a>
		    </li>
		
    </c:forEach>

    <li <c:if test="${!page.isLast()}">class="disabled"</c:if>>
    <!-- 尾页 -->
      <a href="?start=${page.start+page.count}" aria-label="Next">
        <span aria-hidden="true">›</span>
      </a>
    </li>
    <li <c:if test="${!page.isLast()}">class="disabled"</c:if>>
      <a href="?start=${page.last}" aria-label="Next">
        <span aria-hidden="true">»</span>
      </a>
    </li>
  </ul>
</nav>
