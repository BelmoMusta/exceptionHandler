# exceptionHandler
A java 8 lambda based exception handler, it represents a convenient way to get rid of the legacy try-catch blocks in java, 
by making use of the advantages provided by the functoinal behaviour in java 8 and above.

## Example of use 
```java
  TryCatcher tryCatcher = new TryCatcher(() -> {
            int i;
            String s = "a string that cannot be parsed to a number ! ";
            i = Integer.parseInt(s); // exception here
            System.out.println(i);
        }, (throwable) -> throwable.printStackTrace());
```
## Or
``` java
  TryBlock tryBlock = () -> {
  // your code goes here
            throw new Exception(MESSAGE);
        };
        CatchBlock catchBlock =  throwable -> {
        // and handle the thrown exception here
            throwable.printStackTrace();
        };

        TryCatcher tryCatcher = new TryCatcher(tryBlock,catchBlock);
```
