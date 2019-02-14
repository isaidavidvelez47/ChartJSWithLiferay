package com.colfondos.portlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.LiferayPortlet;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=root//ColfondosFuncionarios//cesantias",
		//"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Colfondos-HerramientaCesantias  Portlet",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.single-page-application=false",
	},
	service = Portlet.class
)
public class HerramientaCesantiasPortlet extends GenericPortlet {

	@Override
	protected void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {
	
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher("/view.jsp");
		rd.include(renderRequest, renderResponse);
	}
		
	

}