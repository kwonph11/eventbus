A non thread-safe lightweight, zero-dependency synchronous EventBus using Consumer.andThen() to chain subscribers.

EventBus Main Ideas
- Single Class that can be embedded in any application
- Decouple sender and receiver
- Zero dependency using only standard java library
- Fast subscription logic and can be use in short live components internal communication.
- Easy to use

# Usage
1. Define a message

   ```java  
    public static class Message
    {
        private String txt;
        public Message(String txt)
        {
            this.txt = txt;
        }
    }
    ```
2. Prepare subscribers:

  ```java
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
  ```

 3. Pub/Sub Message

    ```java
        SyncEventBus eventBus = new SyncEventBus();
        Sender sender = new Sender(eventBus);
        eventBus.publish(new Message(txt));
    ```
