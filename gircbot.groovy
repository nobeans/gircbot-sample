@GrabResolver(name="bintray", root="http://dl.bintray.com/nobeans/maven")
@Grab("org.jggug.kobo:gircbot:0.4")
import org.jggug.kobo.gircbot.builder.GircBotBuilder
import org.jggug.kobo.gircbot.core.Job
import org.jggug.kobo.gircbot.core.Reactor
import org.jggug.kobo.gircbot.jobs.Reminder
import org.jggug.kobo.gircbot.jobs.UserScriptJob
import org.jggug.kobo.gircbot.reactors.Dictionary
import org.jggug.kobo.gircbot.reactors.InviteAndByeResponder
import org.jggug.kobo.gircbot.reactors.OpDistributor
import org.jggug.kobo.gircbot.reactors.UserScriptReactor

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

        // The boot distributes "OP (operator permission)" to an user logged in a channel.
        new OpDistributor(),

        // Reacting for "/invite" and "!bye". this is requried when nobody has OP.
        new InviteAndByeResponder(),

        // You can add a script as `Reactor` into a `reactors` directory as you want.
        new UserScriptReactor(new File("reactors")),

        // You can add your local Reactor here.
        new Reactor() {
            void onMessage(String channel, String sender, String login, String hostname, String message) {
                if (message == "OK?") {
                    ircControl.sendNotice(channel, "OK! > $sender")
                }
            }
        },
    )
    jobs(
        // You can define a job which send a message at a specific date and time in `reminder.groovy`.
        new Reminder(new File("reminder.groovy")),

        // You can add a script as `Job` into a `job` directory as you want.
        new UserScriptJob(new File("jobs")),

        // You can add your local Job here.
        new Job() {
            void invoke(Date time) {
                if (time.format("yyyy-MM-dd HH:mm:ss") =~ /:*0$/) {
                    ircControl.sendNotice("#test", "10 seconds passed. ${time.format('HH:mm:ss')}")
                }
            }
        },
    )
}.start()
