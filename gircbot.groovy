@GrabResolver(name="bintray", root="http://dl.bintray.com/nobeans/maven")
@Grab("org.jggug.kobo:gircbot:0.3")
import org.jggug.kobo.gircbot.builder.GircBotBuilder
import org.jggug.kobo.gircbot.reactors.ExternalCommander
import org.jggug.kobo.gircbot.reactors.Dictionary
import org.jggug.kobo.gircbot.reactors.OpDistributor
import org.jggug.kobo.gircbot.reactors.InviteAndByeResponder
import org.jggug.kobo.gircbot.jobs.Reminder

new GircBotBuilder().config {
    server {
        host "localhost"
        port 6667
    }
    nick "gircbot"
    channel {
        autoJoinTo "#test", "#lounge"
    }
    reactors(
        // You can define a static response for a specific keyword in `dictionary.properties`.
        new Dictionary(new File("dictionary.properties")),

        // The boot distributes "OP (operator permission)" to a user
        new OpDistributor(),

        // Reacting for "/invite" and "!bye". this is requried when nobody has OP.
        new InviteAndByeResponder(),

        // You can add a script as `Reactor` into a `command` directory as you want.
        new ExternalCommander(new File("command")),
    )
    jobs(
        // You can define a job which send a message at a specific date and time in `reminder.groovy`.
        new Reminder(new File("reminder.groovy")),
    )
}.start()
