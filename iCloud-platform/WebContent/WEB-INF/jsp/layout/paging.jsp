<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<div class="pagination">
<ul>
  <c:if test="${page.totalPages <= 5 && page.totalPages > 0}">
	  	
	  	
	  	<c:choose>
	  		<c:when test="${page.pageNo ==1}">
	  			 <li class="disabled">
	    			 <a>«</a>
	   			 </li>
	   	    </c:when>	
			<c:otherwise>
				 <li>
					<a href="${url}?page.pageNo=${page.pageNo - 1}" class="prepaging">«</a>
				 </li>
			</c:otherwise>
	  	</c:choose>
	  	
  		
  		 <c:forEach var="x" begin="1" end="${page.totalPages}" step="1">
    		<li class="<c:if test="${x==page.pageNo}">active</c:if>">
    			<a href="${url}?page.pageNo=${x}" class="prepaging">${x}</a>
    		</li>
		</c:forEach>
		
		<c:choose>
	  		<c:when test="${page.totalPages == page.pageNo || page.totalPages ==0}">
	  			 <li <c:if test="${page.totalPages == page.pageNo}">class="disabled"</c:if>>
	    			<a>»</a>
	   			 </li>
	   	    </c:when>	
			<c:otherwise>
				 <li>
					<a href="${url}?page.pageNo=${page.pageNo + 1}" class="prepaging">»</a>
				 </li>
			</c:otherwise>
	  	</c:choose>
  </c:if>  
  <c:if test="${page.totalPages > 5}">
	  <c:choose>
	  		<c:when test="${page.pageNo ==1}">
	  			 <li class="disabled">
	    			 <a>«</a>
	   			 </li>
	   	    </c:when>	
			<c:otherwise>
				 <li>
					<a href="${url}?page.pageNo=${page.pageNo - 1}" class="prepaging">«</a>
				 </li>
			</c:otherwise>
	  	</c:choose>
	    
	    <c:forEach var="x" begin="1" end="5" step="1">
	    		<c:if test="${x <3}">
	    		<li class="<c:if test="${x==page.pageNo}">active</c:if>">
	    			<a href="${url}?page.pageNo=${x}" class="prepaging">${x}</a>
	    		</li>
	    		</c:if>
		</c:forEach>
		
	 	<li class="disabled"><a>...</a></li> 
	 	
		 <c:forEach var="x" begin="${page.totalPages-1}" end="${page.totalPages}" step="1">
	    		<li class="<c:if test="${x==page.pageNo}">active</c:if>">
	    		<a href="${url}?page.pageNo=${x}" class="prepaging">${x}</a>
	    		</li>
		</c:forEach>
	  
	  	<c:choose>
	  		<c:when test="${page.totalPages == page.pageNo || page.totalPages ==0}">
	  			 <li <c:if test="${page.totalPages == page.pageNo}">class="disabled"</c:if>>
	    			<a>»</a>
	   			 </li>
	   	    </c:when>	
			<c:otherwise>
				 <li>
					<a href="${url}?page.pageNo=${page.pageNo + 1}" class="prepaging">»</a>
				 </li>
			</c:otherwise>
	  	</c:choose>
  </c:if>
</ul>
<ul>
 	
 	<li><a> 第 <input  type="text"  class="prepaginggo"  value="${page.pageNo}"  
                style="margin-bottom: -2px; margin-top: -4px; width: 30px; height: 14px; direction: rtl;"> 页  
        </a></li>  
	  <li>
	    <a href="${url}" class="prepaginggo">跳转</a>
	  </li>
 </ul>
 <input type="hidden" class="totalPages" value="${page.totalPages}"/>
</div>