<%@page import="co.com.colfondos.herramientapo.constants.HerramientaPOPortletKeys" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<% int malePensionAge = (int) renderRequest.getAttribute("male_age"); %>
<% int femalePensionAge = (int) renderRequest.getAttribute("female_age"); %>

<% String personGender = (String) renderRequest.getAttribute(HerramientaPOPortletKeys.GENDER_ATTRIBUTE); %>
<% String personAge = (String) renderRequest.getAttribute(HerramientaPOPortletKeys.AGE_ATTRIBUTE); %>
<% String salary = (String) renderRequest.getAttribute(HerramientaPOPortletKeys.SALARY_ATTRIBUTE); %>
<% String balance = (String) renderRequest.getAttribute(HerramientaPOPortletKeys.BALANCE_ATTRIBUTE); %>
<% String projection = (String) renderRequest.getAttribute(HerramientaPOPortletKeys.PROJECTION_ATTRIBUTE); %>
<% String months = (String) renderRequest.getAttribute(HerramientaPOPortletKeys.MONTHS_ATTRIBUTE); %>