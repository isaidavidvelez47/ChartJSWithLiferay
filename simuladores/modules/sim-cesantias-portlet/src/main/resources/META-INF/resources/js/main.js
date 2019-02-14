define(["require", "exports", "@angular/platform-browser-dynamic", "./app/app.component", "./app/app.module", "./app/dynamic.loader"], function (require, exports, platform_browser_dynamic_1, app_component_1, app_module_1, dynamic_loader_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    function default_1(rootId) {
        platform_browser_dynamic_1.platformBrowserDynamic()
            .bootstrapModule(app_module_1.AppModule)
            .then(function (injector) {
            // Load the bootstrap component dinamically so that we can attach it
            // to the portlet's DOM, which is different for each portlet
            // instance and, thus, cannot be determined until the page is
            // rendered (during runtime).
            // The rootId argument is passed from view.jsp where we can obtain
            // the portlet's namespace by using JSP tags.
            var dynamicLoader = new dynamic_loader_1.DynamicLoader(injector);
            dynamicLoader.loadComponent(app_component_1.AppComponent, rootId);
        });
    }
    exports.default = default_1;
});
//# sourceMappingURL=main.js.map