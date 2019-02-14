package co.com.colfondos.tech.ws.cliente.funcionario.cache.liferay;

import co.com.colfondos.tech.ws.cliente.funcionario.cache.delegate.FuncionarioDelegate;
import co.com.colfondos.tech.ws.cliente.funcionario.cache.constants.CacheConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This Class implements <code>WebCacheItem</code> interface
 * to manage PO parameters cache.
 * <p>
 * This Cache manager is based on SingleVMPool which is
 * not aware of multiple nodes
 *
 * @author Pragma S.A. - Guillermo Grajales
 * @version 1.0.0
 */
public class CacheManagerPensionAge implements WebCacheItem {

    /**
     * Constant field <code>REFRESH_TIME</code>
     * indicates interval to clear cache
     */
    private static final long REFRESH_TIME = Time.DAY * 30;

    /**
     * This method is called in case cache is null
     *
     * @param key: {@link String} with cache key
     * @return an {@link Object} of type {@link List<co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.Edades>}
     * @throws WebCacheException
     */
    @Override
    public Object convert(String key) throws WebCacheException {
        try {
            FuncionarioDelegate delegate = new FuncionarioDelegate();

            return delegate.getEdadesPension();
        }catch (Exception e) {
            return null;
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
