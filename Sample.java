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
    
    public static class Receiver
    {
        public Receiver(SyncEventBus eventBus)
        {
            eventBus.subscribe(Message.class, this::onMessage);
        }

        public void onMessage(Message message)
        {
            System.out.println(message.txt);
        }
    }

    public static void main(String[] args) {

        SyncEventBus eventBus = new SyncEventBus();
        Receiver receiver = new Receiver(eventBus);
        eventBus.publish(new Message(txt));
    }
}
