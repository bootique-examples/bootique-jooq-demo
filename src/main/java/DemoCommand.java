import com.google.inject.Inject;
import com.google.inject.Provider;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.jooq.JooqFactory;
import io.bootique.jooq.demo.generated.Tables;
import io.bootique.meta.application.CommandMetadata;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

public class DemoCommand extends CommandWithMetadata {
    private Provider<JooqFactory> jooqFactoryProvider;

    @Inject
    public DemoCommand(Provider<JooqFactory> jooqFactoryProvider) {
        super(CommandMetadata.builder(DemoCommand.class)
                .name("demo")
                .shortName('d')
                .description("Demo command selecting data from db.")
                .build());

        this.jooqFactoryProvider = jooqFactoryProvider;
    }

    @Override
    public CommandOutcome run(Cli cli) {
        DSLContext dslContext = jooqFactoryProvider.get().newContext();

        Result<Record> r = dslContext.select().from(Tables.DOMAIN).fetch();
        r.forEach(v -> System.out.println(v.toString()));
        dslContext.close();

        return CommandOutcome.succeeded();
    }
}
