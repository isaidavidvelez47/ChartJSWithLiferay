define(["require", "exports", "@angular/core/testing", "./prueba.component"], function (require, exports, testing_1, prueba_component_1) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    describe('PruebaComponent', function () {
        var component;
        var fixture;
        beforeEach(testing_1.async(function () {
            testing_1.TestBed.configureTestingModule({
                declarations: [prueba_component_1.PruebaComponent]
            })
                .compileComponents();
        }));
        beforeEach(function () {
            fixture = testing_1.TestBed.createComponent(prueba_component_1.PruebaComponent);
            component = fixture.componentInstance;
            fixture.detectChanges();
        });
        it('should create', function () {
            expect(component).toBeTruthy();
        });
    });
});
//# sourceMappingURL=prueba.component.spec.js.map