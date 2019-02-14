package co.com.colfondos.tech.ws.cliente.funcionario.facade;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.URL;

public class BaseWSFacade {

	/**
	 * Field canal.
	 */
	private String canal;
	
	/**
	
	 * @return the canal */
	public String getCanal() {
		return canal;
	}

	/**
	 * @param canal the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	/**
	 * @param qname
	 * @return
	 */
	protected Service createPortTypeService(QName qname) {
		return createPortTypeService(null, qname);
	}
	
	/**
	 * @param qname
	 * @return
	 */
	protected Service createPortTypeService(URL url, QName qname) {
		Service serviceOut;
		/**
		 * El siguiente cambio de class loader es requerido por un bug en Liferay:
		 * https://issues.liferay.com/browse/LPS-67253
		 * Para que funcione debe relizarse una configuracion a nivel de admin:
		 * 
		 * You have at least one CXF Endpoint created in Control Panel > Configuration > 
		 * System Settings > Foundation > CXF Endpoints. You only need to provide a Context Path
		 * 
		 * You have configured JAX-WS API in Control Panel > Configuration > System Settings > 
		 * Foundation > JAX-WS API making it point to the CXF instance you want it to use by passing the same context path.
		 */
		Thread thread = Thread.currentThread();
		ClassLoader contextClassLoader = thread.getContextClassLoader();
		try {
			thread.setContextClassLoader(Endpoint.class.getClassLoader());
			//Endpoint registration or client creation
			if (url != null)
				serviceOut = Service.create(url, qname);
			else
				serviceOut = Service.create(qname);
		}
		finally {
			thread.setContextClassLoader(contextClassLoader);
		}
		return serviceOut;
	}
	
}
