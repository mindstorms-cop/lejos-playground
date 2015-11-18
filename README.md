# lejos-playground

## Requirements

* Java 7

## Deploying to the robot

```shell
./gradlew build
scp build/libs/*.jar root@10.0.1.1:/home/lejos/programs/
```

## Running program via SSH
cd /home/lejos/programs
jrun -cp playground-1.0.jar org.mindstormscop.drehkreisel.Main

## Connecting to Brick

```shell
ssh root@10.0.1.1
```

Password is empty
