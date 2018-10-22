package cn.webfuse.admin.upms.model;

import javax.persistence.*;

@Table(name = "permission_action")
public class PermissionAction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "action_id")
    private Long actionId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return permission_id
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * @param permissionId
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * @return action_id
     */
    public Long getActionId() {
        return actionId;
    }

    /**
     * @param actionId
     */
    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }
}