<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL var="saveDataMixUrl" id="submitMix"/>

<portlet:resourceURL var="cutDateUrl" id="cutDate"/>

<section class="container">
    <div class="form" id="form_mix">
        <div class="parameters mix">
            <!-- <h3>Monto<sup>*</sup></h3> -->
            <div class="amount_input">
                <label for="<portlet:namespace/>salary">
                    <!--<span class="icon-pesos">$</span>-->
                    <input type="text" name="<portlet:namespace/>salary" id="salary_input"
                           placeholder="<liferay-ui:message key="herramientapv.label.money-amount"/>"
                           inputmode="numeric">
                    <span class="span"><liferay-ui:message key="herramientapv.label.money-amount"/></span>
                </label>
            </div>
            <div class="custom-select">
                <select name="<portlet:namespace/>cut_date" id="date_input" class="date">
                    <option value="" disabled="" selected=""><liferay-ui:message
                            key="herramientapv.label.cut-date"/></option>
                </select>

                <span class="span"><liferay-ui:message key="herramientapv.label.cut-date"/></span>
            </div>
            <div class="input-tooltip">
                <div class="custom-select pensiones">
                    <select name="<portlet:namespace/>current_afp" id="current_afp_mix">
                        <option value="" selected=""><liferay-ui:message key="herramientapv.label.afp"/></option>
                        <option value="proteccion">Protección</option>
                        <option value="porvenir">Porvenir</option>
                        <option value="oldMutual">Old Mutual</option>
                    </select>
                    <span class="span"><liferay-ui:message key="herramientapv.label.afp"/></span>
                </div>
                <div class="form-tooltip">i
                    <span class="tooltip-text">Usa para seleccionar fondo de pensiones actual del usuario</span>
                </div>
            </div>
        </div>
    </div>
    <div class="error-container">
        <span><liferay-ui:message key="herramientapv.sim-error-msg"/></span>
    </div>
    <div class="tables-graphics mix" id="tables_mix_container">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link active" onclick="tablesMixTabClicked()">
                    <button id="mix_tables_button">
                        <p>1</p>
                        <span><liferay-ui:message key="herramientapv.title.tabs.tables"/></span>

                    </button>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="resume_mix_tab">
                    <button id="resume_mix_button" onclick="resumeMixTabClick()">
                        <p>2</p>
                        <span class="long-span"><liferay-ui:message key="herramientapv.title.tabs.resume"/></span>

                    </button>
                </a>
            </li>
        </ul>
        <div class="container-table">
            <!-- Colfondos Table -->
            <div class="single-table">
                <div class="title-table">
                    <span><liferay-ui:message key="herramientapv.title.tables.net-rent"/></span>
                </div>
                <table class="table colfondos responsive" id="table_colfondos">
                    <tbody>
                    <tr class="table-header">
                        <td>Colfondos</td>
                        <td>12 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>24 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>36 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>48 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>60 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.distribution"/></td>
                    </tr>
                    <tr>
                        <td>CLASS TRADICIONAL</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25"><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>CLASS DINÁMICO</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25"><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>CLASS ACCIÓN COLOMBIA</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25"><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>CLASS INT. RENTA FIJA</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25"><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>CLASS INT. RENTA VARIABLE</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="0"><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr class="row-rent">
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.rent-perc"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr class="row-rent">
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.rent-pesos"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="tfoot-error">
                <span class="hidden"></span>
            </div>

            <!-- Protección Table -->
            <div class="single-table no-colf proteccion">
                <div class="title-table">
                    <span><liferay-ui:message key="herramientapv.title.tables.net-rent"/></span>
                </div>
                <table class="table responsive" id="table_proteccion">
                    <tbody>
                    <tr class="table-header">
                        <td>Protección</td>
                        <td>12 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>24 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>36 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>48 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>60 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.distribution"/></td>
                    </tr>
                    <tr>
                        <td>Alta Liquidez Recaudador</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Balanceado Conservador</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Acciones en Pesos</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Renta Fija Dólares</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Acciones en Dólares</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="0" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr class="row-rent">
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.rent-perc"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr class="row-rent">
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.rent-pesos"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <!-- Porvenir Table -->
            <div class="single-table no-colf porvenir">
                <div class="title-table">
                    <span><liferay-ui:message key="herramientapv.title.tables.net-rent"/></span>
                </div>
                <table class="table responsive" id="table_porvenir">
                    <tbody>
                    <tr class="table-header">
                        <td>Porvenir</td>
                        <td>12 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>24 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>36 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>48 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>60 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.distribution"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Efectivo Colombia Pesos</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Diversificado Básico</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Acciones Colombia Pesos
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Renta Fija Int. Dólar</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Acciones Internacionales Dólar</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="0" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr class="row-rent">
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.rent-perc"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr class="row-rent">
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.rent-pesos"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- Old Mutual Table -->
            <div class="single-table no-colf oldMutual">
                <div class="title-table">
                    <span><liferay-ui:message key="herramientapv.title.tables.net-rent"/></span>
                </div>
                <table class="table responsive" id="table_oldMutual">
                    <tbody>
                    <tr class="table-header">
                        <td>Old Mutual</td>
                        <td>12 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>24 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>36 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>48 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td>60 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.distribution"/></td>
                    </tr>
                    <tr>
                        <td>Strategist Liquidez Colombia</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Strategist Moderado</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Acciones Colombia</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Renta Fija Global</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="25" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Acciones USA</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="table-input">
                                <input type="number" value="0" disabled><span>%</span>
                            </div>
                        </td>
                    </tr>
                    <tr class="row-rent">
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.rent-perc"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr class="row-rent">
                        <td><liferay-ui:message key="herramientapv.sub-title.tables.rent-pesos"/></td>
                        <td></td>
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

        <div class="container-resume">
            <div class="buttons-container">
                <button id="tables_button" class="button-no-active" onclick="onTableButtonClicked()">
                    <liferay-ui:message key="herramientapv.title.tabs.tables"/>
                </button>
                <button id="graphs_button" class="button-active" onclick="onGraphsButtonClicked()">
                    <liferay-ui:message key="herramientapv.title.tabs.graphs"/>
                </button>
            </div>
            <div class="graphics-container">
                <div class="graphic" id="graph_oldMutual">
                    <div class="graph-title">
						<span>
                            <liferay-ui:message key="herramientapv.sub-title.tables.final-value-pesos"/>
                            <br>Colfondos vs. Old Mutual
                        </span>
                    </div>
                    <div id="legend_old_container" class="legend-container-mix"></div>
                    <!-- No spaces-->
                    <div id="canvas_old_container" class="canvas-container">
                        <canvas id="chart_old" class="canvas-chart"></canvas>
                    </div>
                </div>

                <div class="graphic" id="graph_proteccion">
                    <div class="graph-title">
						<span>
                            <liferay-ui:message key="herramientapv.sub-title.tables.final-value-pesos"/>
                            <br>Colfondos vs. Protección
                        </span>
                    </div>
                    <div id="legend_prot_container" class="legend-container-mix"></div>
                    <!-- No spaces-->
                    <div id="canvas_prot_container" class="canvas-container">
                        <canvas id="chart_prot" class="canvas-chart"></canvas>
                    </div>
                </div>

                <div class="graphic" id="graph_porvenir">
                    <div class="graph-title">
						<span>
                            <liferay-ui:message key="herramientapv.sub-title.tables.final-value-pesos"/>
                            <br>Colfondos vs. Porvenir
                        </span>
                    </div>
                    <div id="legend_porv_container" class="legend-container-mix"></div>
                    <!-- No spaces-->
                    <div id="canvas_porv_container" class="canvas-container">
                        <canvas id="chart_porv" class="canvas-chart"></canvas>
                    </div>
                </div>
            </div>

            <!-- Resume Tables -->
            <div class="tables-container">
                <div class="single-table oldMutual">
                    <div class="title-table resume"></div>
                    <table class="table  responsive" id="t_resume_oldMutual">
                        <thead>
                        <tr class="table-header">
                            <td>COLFONDOS vs OLD MUTUAL</td>
                            <td>12 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>24 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>36 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>48 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>60 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td><liferay-ui:message key="herramientapv.sub-title.tables.diff-cop"/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><liferay-ui:message key="herramientapv.sub-title.tables.diff-rent"/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>

                    </table>
                </div>

                <div class="single-table proteccion">
                    <div class="title-table resume"></div>
                    <table class="table  responsive" id="t_resume_proteccion">
                        <thead>
                        <tr class="table-header">
                            <td>COLFONDOS vs PROTECCIÓN</td>
                            <td>12 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>24 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>36 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>48 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>60 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td><liferay-ui:message key="herramientapv.sub-title.tables.diff-cop"/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><liferay-ui:message key="herramientapv.sub-title.tables.diff-rent"/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>

                    </table>
                </div>

                <div class="single-table porvenir">
                    <div class="title-table resume"></div>
                    <table class="table responsive" id="t_resume_porvenir">
                        <thead>
                        <tr class="table-header">
                            <td>COLFONDOS vs PORVENIR</td>
                            <td>12 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>24 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>36 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>48 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                            <td>60 <liferay-ui:message key="herramientapv.sub-title.tables.months"/></td>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td><liferay-ui:message key="herramientapv.sub-title.tables.diff-cop"/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><liferay-ui:message key="herramientapv.sub-title.tables.diff-rent"/></td>
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
        </div>
    </div>
</section>

<script type="text/javascript">
    $(function () {
        initValues("<%=renderRequest.getContextPath()%>", "<%=saveDataMixUrl%>");
        loadCutDate("<%=cutDateUrl%>");
    });
</script>