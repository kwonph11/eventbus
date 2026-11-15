import com.tko.eventbus.SyncEventBus;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Sample {

    public static class Message
    {
        private String txt;
        public Message(String txt)
        {
            this.txt = txt;
        }
    }


    public static class Sender
    {
        SyncEventBus eventBus;
        public Sender(SyncEventBus eventBus)
        {
            this.eventBus = eventBus;
        }

        public void sendMessage(String txt)
        {
            eventBus.publish(new Message(txt));
        }
    }

    public static class Receiver
    {
        SyncEventBus eventBus;
        public Receiver(SyncEventBus eventBus)
        {
            this.eventBus = eventBus;
            eventBus.subscribe(Message.class, this::onMessage);
        }

        public void onMessage(Message message)
        {
            System.out.println(message.txt);
        }
    }

    public static void main(String[] args) {

        SyncEventBus eventBus = new SyncEventBus();
        Sender sender = new Sender(eventBus);
        Receiver receiver = new Receiver(eventBus);

        sender.sendMessage("Hello World");
    }
}