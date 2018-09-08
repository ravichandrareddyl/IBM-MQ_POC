# IBM-MQ_POC

## Queues
- DEV.QUEUE.1
- DEV.QUEUE.2

## Topics
- dev/

## Test scenario 
> dev/ topic messages need to be reconciled with messages from DEV.QUEUE.1 and DEV.QUEUE.2

- msgFromQ1 --> published in dev/ topic and DEV.QUEUE.1
- msgFromQ2 --> sent through DEV.QUEUE.2

## RESULT
> So msgFromQ1 should be reconciled and msgFromQ2 will be unreconciled 
