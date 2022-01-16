package me.felix.tired.bridge.event.list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.felix.tired.bridge.event.Event;

@AllArgsConstructor @Getter @Setter
public class RotationEvent extends Event {
    float yaw, pitch;
}
