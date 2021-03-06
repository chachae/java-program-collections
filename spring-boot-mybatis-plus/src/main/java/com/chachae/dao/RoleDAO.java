package com.chachae.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chachae.entity.Role;

import java.util.List;

/**
 * @author chachae
 * @date 2019/12/19 13:39
 */
public interface RoleDAO extends BaseMapper<Role> {

  /**
   * 新增角色权限中间表数据
   *
   * @param roleId 角色id
   * @param permissionId 权限id 集合
   * @return boolean
   */
  boolean saveRelation(Long roleId, Long permissionId);

  /**
   * 删除角色权限中间表数据
   *
   * @param roleId 角色id
   * @return boolean
   */
  boolean removeRelation(Long roleId);

  /**
   * 通过权限id 删除角色权限中间表数据
   *
   * @param permissionId 权限id
   * @return boolean
   */
  boolean removeRelationByPermissionId(Long permissionId);

  /**
   * 通过用户id 查询该用户的全部角色
   *
   * @param userId 用户id
   * @return 角色列表
   */
  List<Role> selectRoleByUserId(Long userId);
}
