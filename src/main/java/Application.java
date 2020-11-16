import io.bootique.BQCoreModule;
import io.bootique.BaseModule;
import io.bootique.Bootique;
import io.bootique.di.Binder;

public class Application extends BaseModule {

    public static void main(String[] args) {
        Bootique.app(args)
                .module(Application.class)
                .autoLoadModules()
                .exec()
                .exit();
    }

    @Override
    public void configure(Binder binder) {
        BQCoreModule.extend(binder)
                .addCommand(DemoCommand.class);
    }
}
