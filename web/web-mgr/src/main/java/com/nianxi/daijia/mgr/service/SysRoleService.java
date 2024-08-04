package com.nianxi.daijia.mgr.service;

import com.nianxi.daijia.model.entity.system.SysRole;
import com.nianxi.daijia.model.query.system.SysRoleQuery;
import com.nianxi.daijia.model.vo.base.PageVo;
import com.nianxi.daijia.model.vo.system.AssginRoleVo;

import java.util.List;
import java.util.Map;

public interface SysRoleService {

    SysRole getById(Long id);

    void save(SysRole sysRole);

    void update(SysRole sysRole);

    void remove(Long id);

    PageVo<SysRole> findPage(Long page, Long limit, SysRoleQuery roleQuery);


    void batchRemove(List<Long> idList);

    Map<String, Object> toAssign(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);

    List<SysRole> findAll();
}
