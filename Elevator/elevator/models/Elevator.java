package Elevator.elevator.models;

import Elevator.elevator.models.action.Action;
import Elevator.elevator.models.action.impl.DoorCloseAction;
import Elevator.elevator.models.action.impl.DoorOpenAction;
import Elevator.elevator.models.action.impl.GoDownAction;
import Elevator.elevator.models.action.impl.GoUpAction;
import Elevator.elevator.models.button.Button;
import Elevator.elevator.models.button.impl.ActionButton;
import Elevator.elevator.models.button.impl.NumberButton;

import java.util.HashMap;
import java.util.Map;

public class Elevator {

    private final int                                       MAX_PEOPLE_ACCOMODATE;
    private final int                                       MAX_FLOORS;
    private final Door                                      elevatorDoor;
    private final State                                     elevatorState;
    private final Display                                   elevatorDisplay;
    private final Map<Integer,Button>                       floorNumbersButton;
    private final Button                                    elevatorDoorOpenButton;
    private final Button                                    elevatorDoorCloseButton;
    private final Map<Integer, Map<Direction,ActionButton>> elevatorRequestButtonMap;

    public Elevator(final int maxNumberOfPeople, final int maxNumberOfFloor) {
        this.MAX_PEOPLE_ACCOMODATE = maxNumberOfPeople;
        this.MAX_FLOORS = maxNumberOfFloor;
        this.elevatorDoor = new Door();
        this.elevatorState = new State(null,false,0);
        this.elevatorDisplay = new Display(elevatorState);

        floorNumbersButton = new HashMap<>();
        for(int i=0; i<=maxNumberOfFloor; i++) {
            floorNumbersButton.put(i, new NumberButton(i));
        }

        Action elevatorDoorOpenAction = new DoorOpenAction(elevatorDoor);
        elevatorDoorOpenButton = new ActionButton(elevatorDoorOpenAction);

        Action elevatorDoorCloseAction = new DoorCloseAction(elevatorDoor);
        elevatorDoorCloseButton = new ActionButton(elevatorDoorCloseAction);

        elevatorRequestButtonMap = new HashMap<>();

        // ground floor request elevator action button
        Action requesteGoUpAction = new GoUpAction();
        ActionButton requesteGoUpActionButton = new ActionButton(requesteGoUpAction);
        Map<Direction, ActionButton> groundFloorRequestElevatorMap = new HashMap();
        groundFloorRequestElevatorMap.put(Direction.UP, requesteGoUpActionButton);
        elevatorRequestButtonMap.put(0, groundFloorRequestElevatorMap);

        // top floor request elevator action button
        Action requesteGoDownAction = new GoDownAction();
        ActionButton requesteGoDownActionButton = new ActionButton(requesteGoDownAction);
        Map<Direction, ActionButton> topFloorRequestElevatorMap = new HashMap();
        topFloorRequestElevatorMap.put(Direction.DOWN, requesteGoDownActionButton);
        elevatorRequestButtonMap.put(maxNumberOfFloor, topFloorRequestElevatorMap);

        for(int i=1; i<maxNumberOfFloor; i++) {
            Map<Direction, ActionButton> floorRequestElevatorMap = new HashMap();
            floorRequestElevatorMap.put(Direction.UP, new ActionButton(new GoUpAction()));
            floorRequestElevatorMap.put(Direction.DOWN, new ActionButton(new GoUpAction()));
            elevatorRequestButtonMap.put(i, floorRequestElevatorMap);
        }
    }

    public State getElevatorState() {
        return elevatorState;
    }

    public int getMaxFloors() {
        return MAX_FLOORS;
    }

    public Door getElevatorDoor() {
        return elevatorDoor;
    }

    public Map<Integer, Map<Direction, ActionButton>> getElevatorRequestButtonMap() {
        return elevatorRequestButtonMap;
    }

    public Map<Integer, Button> getFloorNumbersButton() {
        return floorNumbersButton;
    }

    public Display getElevatorDisplay() {
        return elevatorDisplay;
    }
}
