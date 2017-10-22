package com.akkademy.db.client;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import message.GetRequest;
import message.SetRequest;

import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;
import static scala.compat.java8.FutureConverters.toJava;

public class JClient {
    private final ActorSystem system = ActorSystem.create("ClientSystem", ConfigFactory.load(("client")));
    private final ActorSelection remoteDb;

    public JClient(String remoteAddress) {
        remoteDb = system.actorSelection("akka.tcp://AkkademyDbSystem@" + remoteAddress + "/user/AkkademyDbActor");
    }

    public CompletionStage set(String key, Object value) {
        return toJava(ask(remoteDb, new SetRequest(key, value), 5000));
    }

    public CompletionStage<Object> get(String key) {
        return toJava(ask(remoteDb, new GetRequest(key), 5000));
    }

    public void terminate() {
        system.terminate();
    }
}