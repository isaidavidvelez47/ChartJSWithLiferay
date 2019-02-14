<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:renderURL var="renderUrl">
    <portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>

<section class="container">
    <div id="graphic_section" class="graphic-container">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link active" onclick="onTimeChartButtonClicked()"><span>1</span> <liferay-ui:message key="herramientapo.graphs-3.title"/></a>
                <a class="nav-link-mb active" onclick="onTimeChartButtonClicked()"><span>1</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" onclick="onMaxChartButtonClicked()"><span>2</span> <liferay-ui:message key="herramientapo.graphs-2.title"/></a>
                <a class="nav-link-mb" onclick="onMaxChartButtonClicked()"><span>2</span></a>

            </li>
            <li class="nav-item">
                <a class="nav-link" onclick="onLineChartButtonClicked()"> <span>3</span> <liferay-ui:message key="herramientapo.graphs-1.title"/></a>
                <a class="nav-link-mb" onclick="onLineChartButtonClicked()"><span>3</span></a>
            </li>
        </ul>
        <div class="graphic">
            <div class="graphic-data-container">
                <div>
                    <div class="graphic-text-title">
                        <span><liferay-ui:message key="herramientapo.graphs.title.data"/></span>
                    </div>
                    <div>
                        <a class="link-compare" href="${renderUrl}"><liferay-ui:message key="herramientapo.graphs.link"/></a>
                    </div>
                </div>

                <div class="graphic-text-container">
                    <div class="graphic-text"><strong><liferay-ui:message key="herramientapo.graphs.label.gender"/>:</strong> <%=personGender%></div><!-- No spaces
                ---><div class="graphic-text"><strong><liferay-ui:message key="herramientapo.graphs.label.age"/>:</strong> <%=personAge%></div><!--
                ---><div class="graphic-text"><strong><liferay-ui:message key="herramientapo.graphs.label.balance"/>:</strong> <%=balance%></div><!--
                ---><div class="graphic-text"><strong><liferay-ui:message key="herramientapo.graphs.label.salary"/>:</strong> <%=salary%></div><!--
                ---><div class="graphic-text"><strong><liferay-ui:message key="herramientapo.graphs.label.projection"/>:</strong> <%=projection%></div><!--
                ---><div class="graphic-text"><strong><liferay-ui:message key="herramientapo.graphs.label.months"/>:</strong> <%=months%></div>
                </div>
            </div>
            <div class="title-option"><span id="title_graph">1. <liferay-ui:message key="herramientapo.graphs-1.title"/></span></div>
            <div id="legend_container" class="legend-container"></div>
            <!-- No spaces-->
            <div id="canvas_container" class="canvas-container">
                <canvas id="myChart" class="canvas-chart"></canvas>
            </div>
        </div>
    </div>
</section>

<portlet:resourceURL var="resourceUrl" id="callAjax" />


<script type="text/javascript">
    $(document).ready(function() {
        //console.log('calling ajax');
        $.ajax({
            type: "POST",
            url: '<%=resourceUrl%>',
            success: function(data) {
                processJson(data);
            }
        });
    });
</script>