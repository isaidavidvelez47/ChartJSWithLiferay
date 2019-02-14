<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>

<script src="<%=request.getContextPath()%>/main.bundle.js"></script>
<aui:script use="Liferay.SPA">
	//Force disable SPA from here
	Liferay.SPA.excludedPaths.push("/");
</aui:script>

<app>
	 Loading application... 
</app>
