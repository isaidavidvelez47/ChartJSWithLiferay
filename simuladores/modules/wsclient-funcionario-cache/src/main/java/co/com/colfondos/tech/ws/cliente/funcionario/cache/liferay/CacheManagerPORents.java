package co.com.colfondos.tech.ws.cliente.funcionario.cache.liferay;


import co.com.colfondos.tech.ws.cliente.funcionario.cache.delegate.FuncionarioDelegate;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

import java.util.List;

/**
 * This Class implements <code>WebCacheItem</code> interface
 * to manage PO Rentabilities cache.
 * <p>
 * This Cache manager is based on SingleVMPool which is
 * not aware of multiple nodes
 *
 * @author Pragma S.A. - Guillermo Grajales
 * @version 1.0.0
 */
public class CacheManagerPORents implements WebCacheItem {

	/**
	 * Constant field <code>REFRESH_TIME</code>
	 * indicates interval to clear cache
	 */
	private static final long REFRESH_TIME = Time.DAY * 10;

	/**
	 * This method is called in case cache is null
	 *
	 * @param key: {@link String} with cache key
	 * @return an {@link Object} of type {@link List<co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.RentaPortafolioVO>}
	 * @throws WebCacheException
	 */
	@Override
	public Object convert(String key) throws WebCacheException {
		try {
			FuncionarioDelegate delegate = new FuncionarioDelegate();
			return delegate.getRentaValues("PO");
		} catch (Exception e) {
			throw new WebCacheException();
		}
	}

	/**
	 * This method indicates the time required to
	 * refresh cache
	 *
	 * @return long indicating refresh time
	 */
	@Override
	public long getRefreshTime() {
		return REFRESH_TIME;
	}

}