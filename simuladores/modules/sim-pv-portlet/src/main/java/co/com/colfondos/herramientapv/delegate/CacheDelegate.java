package co.com.colfondos.herramientapv.delegate;

import co.com.colfondos.tech.ws.cliente.funcionario.cache.constants.CacheConstants;
import co.com.colfondos.tech.ws.cliente.funcionario.cache.liferay.CacheManagerPVCutDate;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import java.time.LocalDate;

import static co.com.colfondos.herramientapv.constants.HerramientaPVPortletKeys.GLOBAL_PACKAGE_LOGGER;

public class CacheDelegate {

    private static Log LOGGER = LogFactoryUtil.getLog(GLOBAL_PACKAGE_LOGGER);

    /**
     * Instance of {@link CacheDelegate}
     */
    private static CacheDelegate INTANCE = null;

    /**
     * Fields representing cache objects
     */
    private static WebCacheItem cacheItemCutDate;

    /**
     * Sole constructor
     * Initiates cache fields
     */
    private CacheDelegate() {
        cacheItemCutDate = new CacheManagerPVCutDate();
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

    public LocalDate getCutDate() {
        LocalDate cutDate;

        try {
            // It will check cache data in portal cache against the key. If
            // cache data found, it will return cache data. If not found,
            // convert() method of CacheManagerPORents call and put return data in
            // portal cache. The portal cache automatically clear cache after
            // refresh time returned in getRefreshTime().
            cutDate = (LocalDate) WebCachePoolUtil.get(CacheConstants.CACHE_PV_CUT_DATE_KEY, cacheItemCutDate);
        } catch (Exception e) {
            // Sometimes ClassCastException could be thrown. It comes when you
            // redeploy portlet and WebCachePool already have data against the
            // key from previous deployed instance. To avoid this, you need to
            // remove cache for the key and call get() method again.
            WebCachePoolUtil.remove(CacheConstants.CACHE_PV_CUT_DATE_KEY);
            cutDate = (LocalDate) WebCachePoolUtil.get(CacheConstants.CACHE_PV_CUT_DATE_KEY, cacheItemCutDate);
        }

        return cutDate;
    }

    /**
     * This method clears all existent cache
     *
     */
    public void clearAllCache() {
        WebCachePoolUtil.clear();
    }
}
