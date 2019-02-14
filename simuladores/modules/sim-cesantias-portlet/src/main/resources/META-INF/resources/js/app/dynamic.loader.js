define(["require", "exports", "@angular/core"], function (require, exports, core_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var DynamicLoader = /** @class */ (function () {
        function DynamicLoader(injector) {
            this.injector = injector;
        }
        // Load an Angular component dinamically so that we can attach it to
        // the portlet's DOM, which is different for each portlet instance and,
        // thus, cannot be determined until the page is rendered (during runtime).
        DynamicLoader.prototype.loadComponent = function (component, dom) {
            var _this = this;
            this.injector.get(core_1.NgZone).run(function () {
                var componentFactory = _this.injector
                    .get(core_1.ComponentFactoryResolver)
                    .resolveComponentFactory(component);
                var componentRef = componentFactory.create(_this.injector, [], dom);
                _this.injector.get(core_1.ApplicationRef).attachView(componentRef.hostView);
            });
        };
        return DynamicLoader;
    }());
    exports.DynamicLoader = DynamicLoader;
});
//# sourceMappingURL=dynamic.loader.js.map