package co.com.colfondos.tech.ws.cliente.funcionario.cache.liferay;

import co.com.colfondos.tech.ws.cliente.funcionario.cache.constants.CacheConstants;
import co.com.colfondos.tech.ws.cliente.funcionario.cache.delegate.FuncionarioDelegate;
import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.SalarioMinimo;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheManagerMinSalary implements WebCacheItem {

    /**
     * Constant field <code>REFRESH_TIME</code>
     * indicates interval to clear cache
     */
    private static final long REFRESH_TIME = Time.DAY * 30;

    /**
     * This method is called in case cache is null
     *
     * @param key: {@link String} with cache key
     * @return an {@link Object} of type {@link List<co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.SalarioMinimo>}
     * @throws WebCacheException
     */
    @Override
    public Object convert(String key) throws WebCacheException {
        try {
            FuncionarioDelegate delegate = new FuncionarioDelegate();

            return delegate.getSalarioMinimo(LocalDate.now());
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public long getRefreshTime() {
        return REFRESH_TIME;
    }
}
