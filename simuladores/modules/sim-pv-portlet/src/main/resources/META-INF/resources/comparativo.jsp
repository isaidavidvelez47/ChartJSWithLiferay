<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL var="saveDataUrl" id="submitNormal" />

<portlet:resourceURL var="cutDateUrl" id="cutDate" />

<!-- Modal for Loader -->
<div id="load-page">
    <div class="lds-spinner">
        <div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div>
    </div>
</div>
<section class="container">
    <div class="form" id="form_normal">
        <div class="parameters">
            <!-- <h3>Monto<sup>*</sup></h3> -->
            <div class="amount_input">
                <label for="<portlet:namespace/>salary">
                <!--<span class="icon-pesos">$</span>-->
                <input type="text" name="<portlet:namespace/>salary" id="salary_input"
                       placeholder="<liferay-ui:message key="herramientapv.label.money-amount"/>"  inputmode="numeric">
                <span class="span"><liferay-ui:message key="herramientapv.label.money-amount"/></span>
            </label>
            </div>
            <!-- <h3>Fecha de corte<sup>*</sup></h3> -->
            <div class="custom-select">
                <select name="<portlet:namespace/>cut_date" id="date_input" class="date">
                    <option value="" disabled="" selected=""><liferay-ui:message key="herramientapv.label.cut-date"/></option>
                </select>

                <span class="span"><liferay-ui:message key="herramientapv.label.cut-date"/></span>
            </div>
            <div class="custom-select colfondos">
                <select name="<portlet:namespace/>projection_type" id="sel_projection">
                    <option value="" disabled="" selected=""><liferay-ui:message key="herramientapv.label.colfondos-class"/></option>
                    <option value=0>CLASS_TRADICIONAL</option>
                    <option value=1>CLASS_DINÁMICO</option>
                    <option value=2>CLASS_ACCIÓN_COLOMBIA</option>
                    <option value=4>CLASS_INTERNACIONAL_RENTA_FIJA</option>
                    <option value=3>CLASS_INTERNACIONAL_RENTA_VARIABLE</option>
                </select>
                <span class="span"><liferay-ui:message key="herramientapv.label.colfondos-class"/></span>
            </div>
            <div class="custom-select meses">
                <select name="<portlet:namespace/>months_period" id="sel_months">
                    <option value="" disabled="" selected=""><liferay-ui:message key="herramientapv.label.months"/></option>
                    <option>12</option>
                    <option>24</option>
                    <option>36</option>
                    <option>48</option>
                    <option>60</option>
                </select>
                <span class="span"><liferay-ui:message key="herramientapv.label.months"/></span>
            </div>
            <div class="input-tooltip">
                <div class="custom-select pensiones">
                    <select name="<portlet:namespace/>current_afp" id="current_afp">
                        <option value="" selected=""><liferay-ui:message key="herramientapv.label.afp"/></option>
                        <option value="proteccion">Protección</option>
                        <option value="porvenir">Porvenir</option>
                        <option value="oldMutual">Old Mutual</option>
                    </select>
                    <span class="span"><liferay-ui:message key="herramientapv.label.afp"/></span>
                </div>
                <div class="form-tooltip">i
                    <span class="tooltip-text"><liferay-ui:message key="herramientapv.label.tooltip"/></span>
                </div>
            </div>

        </div>
    </div>
    <div class="error-container">
        <span><liferay-ui:message key="herramientapv.sim-error-msg"/></span>
    </div>
    <div class="tables-graphics" id="tables_container">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link active">
                    <button onclick="showNormalTables()">
                        <p>1</p>
                        <span><liferay-ui:message key="herramientapv.title.tabs.tables"/></span>
                    </button>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link">
                    <button onclick="showGraphs()">
                        <p>2</p>
                        <span><liferay-ui:message key="herramientapv.title.tabs.graphs"/></span>
                    </button>
                </a>
            </li>
        </ul>
        <div class="container-table">
            <div class="single-table">
                <div class="title-table">
                    <span><liferay-ui:message key="herramientapv.title.tables.net-rent"/></span>
                </div>
                <table class="table t-normal responsive" id="table_rents_normal">
                    <!--
                <thead class="title-table">
                    <tr class="border-cell">
                        <td colspan="7" cellspacing="0"></td>
                    </tr>
                    <tr>
                        <td COLSPAN="7"><liferay-ui:message key="herramientapv.title.tables.net-rent"/></td>
                    </tr>
                </thead>
                -->
                    <tbody>
                    <tr class="table-header">
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.afps"/></td>
                        <td>12 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>24 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>36 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>48 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>60 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                    </tr>
                    <tr id="row_colf_r">
                        <td>Class Internacional Renta fija</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="row_proteccion_r" class="no-colf-row">
                        <td>Protección Renta Fija Dólares</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="row_oldMutual_r" class="no-colf-row">
                        <td>Old Mutual Renta Fija Global</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="row_porvenir_r" class="no-colf-row">
                        <td>Porvenir - Renta fija Global</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>

                </table>
            </div>

            <div class="single-table">
                <div class="title-table">
                    <span><liferay-ui:message key="herramientapv.title.tables.final-value"/></span>
                </div>
                <table class="table t-normal responsive" id="table_finalv">
                    <tbody>
                    <tr class="table-header">
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.afps"/></td>
                        <td>12 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>24 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>36 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>48 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>60 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                    </tr>
                    <tr id="row_colf_v">
                        <td>Class Internacional Renta fija</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="row_proteccion_v" class="no-colf-row">
                        <td>Protección Renta Fija Dólares</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="row_oldMutual_v" class="no-colf-row">
                        <td>Old Mutual Renta Fija Global</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="row_porvenir_v" class="no-colf-row">
                        <td>Porvenir - Renta fija Global</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>

                </table>
            </div>

        </div>

        <div class="container-graphs" style="display: none;">

            <div class="graphic" id="first_graph">
                <div class="graph-title">
                    <span><liferay-ui:message key="herramientapv.title.graphs.pesos-perc"/></span>
                </div>
                <div id="legend_bar_container" class="legend-container"></div>

                <!-- No spaces-->
                <div class="sup-legend-container">
                    <div class="legend-square"><div></div><span><liferay-ui:message key="herramientapv.graphs.legend.final-value"/></span></div>
                    <div class="legend-dashed"><div></div><span><liferay-ui:message key="herramientapv.graphs.legend.rent"/></span></div>
                </div>
                <div id="bar_canvas_container" class="canvas-container">
                    <canvas id="bar_chart" class="canvas-chart"></canvas>
                </div>
            </div>

            <div class="graphic">
                <div class="graph-title">
                    <span><liferay-ui:message key="herramientapv.title.graphs.evol"/></span>
                </div>
                <div id="legend_line_container" class="legend-container show-legend"></div>
                <!-- No spaces-->
                <div id="line_canvas_container" class="canvas-container">
                    <canvas id="line_chart" class="canvas-chart"></canvas>
                </div>
            </div>

            <div class="graphic">
                <div class="graph-title">
                    <span><liferay-ui:message key="herramientapv.title.graphs.volatility"/></span>
                </div>
                <div id="legend_scatter_container" class="legend-container show-legend"></div>
                <!-- No spaces-->
                <div id="volat_canvas_container" class="canvas-container">
                    <canvas id="scatter_chart" class="canvas-chart"></canvas>
                </div>
            </div>

            <div class="graphic">
                <div class="graph-title">
                    <span><liferay-ui:message key="herramientapv.title.graphs.drawdown"/></span>
                </div>
                <div id="legend_single_container" class="legend-container show-legend"></div>
                <!-- No spaces-->
                <div id="down_canvas_container" class="canvas-container">
                    <canvas id="single_chart" class="canvas-chart"></canvas>
                </div>
            </div>
        </div>
    </div>
</section>

<script type="text/javascript">
    $(function() {
        initValues("<%=renderRequest.getContextPath()%>", "<%=saveDataUrl%>");
        loadCutDate("<%=cutDateUrl%>");
    });
</script>