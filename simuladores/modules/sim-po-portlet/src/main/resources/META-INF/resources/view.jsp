<%@ include file="/META-INF/resources/init.jsp" %>

<portlet:resourceURL var="resourceUrl" id="initParams" />

<section class="container">
    <portlet:actionURL var="submitURL" name="saveData" />
    <div class="form-container">
        <form class="form-js-label" action="<%=submitURL%>" method="POST" id="my_form">
            <div class="gender-age">
                <div class="col-left">
                    <span><liferay-ui:message key="herramientapo.form.label.gender"/></span>
                    <div style="margin-top: 0;">
                        <button class="button-image" type="button" id="male_button" onclick="onGenderButtonClicked(this.id)">
                            <img id="male_img" width="80" height="80" src="${renderRequest.contextPath}/assets/icono_hombre.png">
                        </button>
                        <button class="button-image" type="button" id="female_button" onclick="onGenderButtonClicked(this.id)">
                            <img id="female_img" width="80" height="80" src="${renderRequest.contextPath}/assets/icono_mujer_activa.png">
                        </button>
                        <!-- Use hidden field to assign table value i.e. "male" or "female" -->
                        <input type="hidden" name="<portlet:namespace/>person_gender" id="tableTextId" />
                    </div>
                </div>
                <div class="col-right">
                    <!-- <h3>Edad<sup>*</sup></h3> -->
                    <div class="age-content">
                        <label for="<portlet:namespace/>person_age">
                            <input type="number" id="age_input" oninput="this.value=this.value.replace(/[^0-9]/g,'')"
                                   placeholder="Edad*" name="<portlet:namespace/>person_age"
                                   style="width: 100%" min="18" required>
                            <span class="span"><liferay-ui:message key="herramientapo.form.label.age"/></span>
                        </label>

                        <div class="slider-content">
                            <span class="slider_label" id="min_slider">18</span>
                            <input id="age_slider" type="range" min="18" max="<%=(femalePensionAge-1)%>" value="50">
                            <span class="slider_label" id="max_slider"><%=femalePensionAge-1%></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="parameters">
                <div class="row-flex">
                    <div class="custom-select">
                        <select id="projection_select" name="<portlet:namespace/>projection_type" required>
                            <option value="" disabled="" selected=""><liferay-ui:message key="herramientapo.form.label.projection"/></option>
                            <option value="actual"><liferay-ui:message key="herramientapo.form.text.actual"/></option>
                            <option value="esperado"><liferay-ui:message key="herramientapo.form.text.esperada"/></option>
                            <option value="optimistico"><liferay-ui:message key="herramientapo.form.text.optimista"/></option>
                            <option value="conservador"><liferay-ui:message key="herramientapo.form.text.conservadora"/></option>
                        </select>
                        <span class="span"><liferay-ui:message key="herramientapo.form.label.projection"/><sup>1</sup></span>
                        <span id="projection_description" class="span-description"><liferay-ui:message key="herramientapo.form.text.info"/></span>
                    </div>

                    <div class="currency_input">
                        <label for="<portlet:namespace/>person_balance">
                            <input type="text" name="<portlet:namespace/>person_balance" placeholder="<liferay-ui:message key="herramientapo.form.label.balance"/>" required>
                            <span class="span"><liferay-ui:message key="herramientapo.form.label.balance"/></span></label>
                    </div>
                </div>
                <div class="row-flex">
                    <!-- Salary Input -->
                    <div class="currency_input">
                        <label for="<portlet:namespace/>person_salary">
                            <input type="text" placeholder="<liferay-ui:message key="herramientapo.form.label.salary"/>" name="<portlet:namespace/>person_salary" required>
                            <span class="span"><liferay-ui:message key="herramientapo.form.label.salary"/></span>
                        </label>
                    </div>

                    <!-- <h3>Meses en tomar la decisión<sup>*</sup></h3> -->
                    <div class="custom-select">
                        <label for="<portlet:namespace/>decision_months">
                            <input type="number" class="months-input" name="<portlet:namespace/>decision_months"
                                   min="1" oninput="this.value=this.value.replace(/[^0-9]/g,'')"
                                   placeholder="<liferay-ui:message key="herramientapo.form.label.months"/>" required>
                            <span class="span"><liferay-ui:message key="herramientapo.form.label.months"/></span>
                        </label>
                    </div>
                </div>
            </div>
            <div class="afp_type">
                <h2><liferay-ui:message key="herramientapo.form.text"/></h2>
                <div class="col-left">
                    <span class=""><liferay-ui:message key="herramientapo.form.label.afp"/></span>
                    <div class="types">
                        <div class="btn-afp">
                            <img class="img-afp" onclick="onAFPClicked(this.id)" id="img_colfondos"
                                 src="${renderRequest.contextPath}/assets/colfondos_bn.png" alt="">
                        </div>
                        <div class="btn-afp">
                            <img class="img-afp" onclick="onAFPClicked(this.id)" id="img_porvenir"
                                 src="${renderRequest.contextPath}/assets/porvenir_bn.png" alt="">
                        </div>
                        <div class="btn-afp">
                            <img class="img-afp" onclick="onAFPClicked(this.id)" id="img_proteccion"
                                 src="${renderRequest.contextPath}/assets/proteccion_bn.png" alt="">
                        </div>
                        <div class="btn-afp">
                            <img class="img-afp" onclick="onAFPClicked(this.id)" id="img_old_m"
                                 src="${renderRequest.contextPath}/assets/old_mutual_bn.png" alt="">
                        </div>
                        <!-- Use hidden field to assign AFP value i.e. "male" or "female" -->
                        <input type="hidden" name="<portlet:namespace/>afp_name" id="afp_input" />
                    </div>
                    <div id="error_afp" style="visibility: hidden">
                        <span style="color: red"><liferay-ui:message key="herramientapo.form.text.error"/></span>
                    </div>
                </div>
                <div class="col-right">
                    <div class="custom-select">
                        <select name="<portlet:namespace/>fund_type" required>
                            <option value="" disabled="" selected=""><liferay-ui:message key="herramientapo.form.label.fund"/></option>
                            <option value="Conservador"><liferay-ui:message key="herramientapo.form.text.conservador"/></option>
                            <option value="Moderado"><liferay-ui:message key="herramientapo.form.text.moderado"/></option>
                        </select>
                        <span class="span"><liferay-ui:message key="herramientapo.form.label.fund"/></span>
                    </div>
                    <input type="button" class="btn-compare" name="saveButton" value="Comparar" onclick="validateForm()">
                    <!-- Use hidden button to submit -->
                    <input type="submit" style="visibility: hidden" />
                </div>

            </div>
            <div class="mandatory-text">
                <span><liferay-ui:message key="herramientapo.form.text.mandatory"/></span>
            </div>
            <div class="disclaimer-container hidden">
                <div>
                    <span><sup>1</sup></span>
                </div>
                <div>
                    <span><b><liferay-ui:message key="herramientapo.form.text.actual"/>:</b> <liferay-ui:message key="herramientapo.form.text.info.actual"/></span>
                    <span><b><liferay-ui:message key="herramientapo.form.text.conservador"/>:</b> <liferay-ui:message key="herramientapo.form.text.info.conservadora"/></span>
                    <span><b><liferay-ui:message key="herramientapo.form.text.esperada"/>:</b> <liferay-ui:message key="herramientapo.form.text.info.esperada"/></span>
                    <span><b><liferay-ui:message key="herramientapo.form.text.optimista"/>:</b> <liferay-ui:message key="herramientapo.form.text.info.optimista"/></span>
                </div>
            </div>
        </form>

    </div>

</section>

<script>
    $(document).ready(function() {
        initValues(<%=malePensionAge%>, <%=femalePensionAge%>, '<%=renderRequest.getContextPath()%>');
        loadInitParams('<%=resourceUrl%>');
    })
</script>