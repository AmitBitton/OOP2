package gym.management;

import gym.customers.Client;

import java.util.HashSet;
import java.util.Set;

abstract class Subject {
    protected final Set<Client> clientSet= new HashSet<>();
    public void subscribe(Client client){
        clientSet.add(client);
    }
    public void unSubscribe(Client client){
        clientSet.remove(client);
    }

    public void notifyClients(String notification) {
        for (Client client : clientSet) {
            client.update(notification);
        }
    }

}
