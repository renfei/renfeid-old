package net.renfei.services.leaf.common;


import net.renfei.services.leaf.IDGen;

public class ZeroIDGen implements IDGen {
    @Override
    public Result get(String key) {
        return new Result(0, StatusEnum.SUCCESS);
    }

    @Override
    public boolean init() {
        return true;
    }
}
