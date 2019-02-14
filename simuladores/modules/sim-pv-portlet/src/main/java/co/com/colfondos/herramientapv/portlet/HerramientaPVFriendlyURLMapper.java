package co.com.colfondos.herramientapv.portlet;

import co.com.colfondos.herramientapv.constants.HerramientaPVPortletKeys;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml",
                "javax.portlet.name=" + HerramientaPVPortletKeys.HERRAMIENTA_PV
        },
        service = FriendlyURLMapper.class
)
public class HerramientaPVFriendlyURLMapper extends DefaultFriendlyURLMapper {

    private static final String _MAPPING = "herramienta-pv";

    @Override
    public String getMapping() {
        return _MAPPING;
    }
}
