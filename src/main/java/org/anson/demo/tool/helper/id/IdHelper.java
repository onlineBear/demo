package org.anson.demo.tool.helper.id;

import org.anson.demo.tool.util.id.IdWorker;

/**
 * id 生成 (主键生成)
 */
public class IdHelper {
    private static final IdWorker idWorker = new IdWorker(0, 0);

    /**
     * 下一个 id
     * @return
     */
    public static Long nextId(){
        return idWorker.nextId();
    }
}
