package action;

import base.GameObject;

public class LimitAction implements Action {

    private Action action;

    private int count;

    public LimitAction(int count, Action action) {
        this.count = count;
        this.action = action;
    }

    @Override
    public boolean run(GameObject owner) {
        if (this.action.run(owner)) {
            this.action.reset();
            if (this.count == 1) {
                return true;
            } else {
                this.count -= 1;
            }
        }
        return false;
    }

    @Override
    public void reset() {

    }
}
