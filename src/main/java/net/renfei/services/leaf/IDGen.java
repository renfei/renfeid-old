package net.renfei.services.leaf;

import net.renfei.services.leaf.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
