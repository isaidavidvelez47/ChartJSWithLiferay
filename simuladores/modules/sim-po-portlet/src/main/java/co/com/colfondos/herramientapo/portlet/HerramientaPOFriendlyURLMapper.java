package co.com.colfondos.herramientapo.portlet;

import co.com.colfondos.herramientapo.constants.HerramientaPOPortletKeys;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml",
                "javax.portlet.name=" + HerramientaPOPortletKeys.HerramientaPO
        },
        service = FriendlyURLMapper.class
)
public class HerramientaPOFriendlyURLMapper extends DefaultFriendlyURLMapper {

    private static final String _MAPPING = "herramienta-po";

    @Override
    public String getMapping() {
        return _MAPPING;
    }
}
