package co.com.colfondos.herramientapo.delegate;

import co.com.colfondos.tech.ws.cliente.funcionario.cache.liferay.CacheManagerMinSalary;
import co.com.colfondos.tech.ws.cliente.funcionario.cache.liferay.CacheManagerPensionAge;
import co.com.colfondos.tech.ws.cliente.funcionario.cache.liferay.CacheManagerPORents;
import co.com.colfondos.tech.ws.cliente.funcionario.cache.constants.CacheConstants;
import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.Edades;
import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.RentaPortafolioVO;
import co.com.colfondos.tech.ws.cliente.funcionario.service_funcionario.SalarioMinimo;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import java.util.List;
import java.util.Map;

/**
 * This class is used to access the cache manager
 *
 * @author Pragma S.A. - Guillermo Grajales
 * @version 1.0.0
 */
public class CacheDelegate {

    /**
     * Instance of {@link CacheDelegate}
     */
    private static CacheDelegate INTANCE = null;

    /**
     * Fields representing cache objects
     */
    private static WebCacheItem cacheItemRents;
    private static WebCacheItem cacheItemPensionAge;
    private static WebCacheItem cacheItemMinSalary;

    /**
     * Sole constructor
     * Initiates cache fields
     */
    private CacheDelegate() {
        cacheItemRents = new CacheManagerPORents();
        cacheItemPensionAge = new CacheManagerPensionAge();
        cacheItemMinSalary = new CacheManagerMinSalary();
    }

    /**
     * Method to obtain an instance of {@link CacheDelegate}
     * <p>
     * This method checks if there's an existence instance
     *
     * @return an object of {@link CacheDelegate}
     */
    public static synchronized CacheDelegate getInstance() {
        if (INTANCE == null) {
            INTANCE = new CacheDelegate();
        }

        return INTANCE;
    }

    /**
     * This method access to cache manager to obtain Rentabilities.
     * <p>
     * When there's not cache data, the rentabilities are read from
     * WS provided by Colfondos
     *
     * @return a {@link List<RentaPortafolioVO>}
     */
    @SuppressWarnings("unchecked")
    public List<RentaPortafolioVO> getRentabilidadesPO() {
        List<RentaPortafolioVO> resultsList;
        try {
            // It will check cache data in portal cache against the key. If
            // cache data found, it will return cache data. If not found,
            // convert() method of CacheManagerPORents call and put return data in
            // portal cache. The portal cache automatically clear cache after
            // refresh time returned in getRefreshTime().
            resultsList = (List<RentaPortafolioVO>) WebCachePoolUtil.get(CacheConstants.CACHE_RENTPO_KEY, cacheItemRents);
        } catch (Exception e) {
            // Sometimes ClassCastException could be thrown. It comes when you
            // redeploy portlet and WebCachePool already have data against the
            // key from previous deployed instance. To avoid this, you need to
            // remove cache for the key and call get() method again.
            WebCachePoolUtil.remove(CacheConstants.CACHE_RENTPO_KEY);
            resultsList = (List<RentaPortafolioVO>) WebCachePoolUtil.get(CacheConstants.CACHE_RENTPO_KEY, cacheItemRents);
        }

        return resultsList;
    }

    /**
     * This method access to cache manager to obtain Min Salary.
     * <p>
     * When there's not cache data, the min salary is read from
     * WS provided by Colfondos
     *
     * @return a {@link List<SalarioMinimo>}
     */
    @SuppressWarnings("unchecked")
    public List<SalarioMinimo> getSalarioMinimo() {
        List<SalarioMinimo> resultsList;

        try {
            // It will check cache data in portal cache against the key. If
            // cache data found, it will return cache data. If not found,
            // convert() method of CacheManagerPORents call and put return data in
            // portal cache. The portal cache automatically clear cache after
            // refresh time returned in getRefreshTime().
            resultsList = (List<SalarioMinimo>) WebCachePoolUtil.get(CacheConstants.CACHE_MIN_SALARY_KEY, cacheItemMinSalary);

        } catch (Exception e) {
            // Sometimes ClassCastException could be thrown. It comes when you
            // redeploy portlet and WebCachePool already have data against the
            // key from previous deployed instance. To avoid this, you need to
            // remove cache for the key and call get() method again.
            WebCachePoolUtil.remove(CacheConstants.CACHE_MIN_SALARY_KEY);
            resultsList = (List<SalarioMinimo>) WebCachePoolUtil.get(CacheConstants.CACHE_MIN_SALARY_KEY, cacheItemMinSalary);

        }

        return resultsList;
    }

    /**
     * This method access to cache manager to obtain Pension ages.
     * <p>
     * When there's not cache data, the ages are read from
     * WS provided by Colfondos
     *
     * @return a {@link List<Edades>}
     */
    @SuppressWarnings("unchecked")
    public List<Edades> getEdadesPension() {
        List<Edades> resultsList;

        try {
            // It will check cache data in portal cache against the key. If
            // cache data found, it will return cache data. If not found,
            // convert() method of CacheManagerPORents call and put return data in
            // portal cache. The portal cache automatically clear cache after
            // refresh time returned in getRefreshTime().
            resultsList = (List<Edades>) WebCachePoolUtil.get(CacheConstants.CACHE_PENSION_AGE_KEY, cacheItemPensionAge);


        } catch (Exception e) {
            // Sometimes ClassCastException could be thrown. It comes when you
            // redeploy portlet and WebCachePool already have data against the
            // key from previous deployed instance. To avoid this, you need to
            // remove cache for the key and call get() method again.
            WebCachePoolUtil.remove(CacheConstants.CACHE_PENSION_AGE_KEY);
            resultsList = (List<Edades>) WebCachePoolUtil.get(CacheConstants.CACHE_PENSION_AGE_KEY, cacheItemPensionAge);
        }

        return resultsList;
    }

    /**
     * This method clears all existent cache
     *
     */
    public void clearAllCache() {
        WebCachePoolUtil.clear();
    }

    /**
     * This method is used to remove a specific cache
     * identified by a key
     *
     * @param key: {@link String} that represents the cache key
     */
    public void removeCacheByKey(String key) {
        WebCachePoolUtil.remove(key);
    }
}
