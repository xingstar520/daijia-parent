package com.nianxi.daijia.system.service;

import com.nianxi.daijia.model.entity.system.SysRole;
import com.nianxi.daijia.model.query.system.SysRoleQuery;
import com.nianxi.daijia.model.vo.base.PageVo;
import com.nianxi.daijia.model.vo.system.AssginRoleVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {

    PageVo<SysRole> findPage(Page<SysRole> pageParam, SysRoleQuery roleQuery);

    /**
     * 根据用户获取角色数据
     * @param userId
     * @return
     */
    Map<String, Object> findRoleByUserId(Long userId);

    /**
     * 分配角色
     * @param assginRoleVo
     */
    void doAssign(AssginRoleVo assginRoleVo);
}
