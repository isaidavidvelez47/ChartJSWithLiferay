<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:renderURL var="comparativoURL">
	<portlet:param name="mvcPath" value="/comparativo.jsp"/>
</portlet:renderURL>

<portlet:renderURL var="comparativoMixURL">
	<portlet:param name="mvcPath" value="/comparativoMix.jsp"/>
</portlet:renderURL>


<section class="container">
	<div class="options-container">
		<a class="comparativo" href="<%=comparativoURL%>">
			<div><img src="${renderRequest.contextPath}/assets/icono_compare.png" alt="icono-comparativo" style="width: 100%;"></div>
			<span class="text">Comparativo de Portafolio Colfondos vs AFP'S</span>
		</a>
		<a class="comparativo" href="<%=comparativoMixURL%>">
			<!--<span class="icon-comparative">-->
				<!--<img src="${renderRequest.contextPath}/assets/icono_compare.png" alt="icono-comparativo">-->
			<div><img src="${renderRequest.contextPath}/assets/icono_compare.png" alt="icono-comparativo" style="width: 100%;"></div>
			<span class="text">Comparativo mix de Portafolio Colfondos vs AFP'S</span>
		</a>
	</div>
</section>