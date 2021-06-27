<template>
    <div class="index">
        <el-container class="index-container">
            <el-header>
                <span>简易教务管理系统</span>
                <span class="nickname">{{nickname}}</span>
                <el-button type="primary" @click="logout()" class="logout">退出登录</el-button>
            </el-header>
            <el-container>
                <el-aside width="180px">
                    <el-row>
                        <el-col>
                            <el-menu
                                    background-color="#545c64"
                                    text-color="#fff"
                                    active-text-color="#ffd04b"
                                    router
                                    :default-active="active"
                            >
                                <el-submenu :index="item.menu.path" v-for="item in menuList"
                                            :key="item.menu.permissionId">
                                    <template slot="title">
                                        <i class="el-icon-location"></i>
                                        <span>{{item.menu.permissionName}}</span>
                                    </template>
                                    <el-menu-item :index="it.path" v-for="it in item.subMenu"
                                                  :key="it.permissionId"
                                    @click="saveActive(it.path)">
                                        {{it.permissionName}}
                                    </el-menu-item>
                                </el-submenu>
                            </el-menu>
                        </el-col>
                    </el-row>
                </el-aside>
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "index",
        created() {
            this.getMenuList();
            this.nickname = this.$store.getters.getUser.nickname;
        },
        methods: {
            getMenuList() {
                var username
                if (this.$store.getters.getUser == ""||this.$store.getters.getUser == null){
                    username = "";
                }else {
                    username = this.$store.getters.getUser.username
                }
                this.$axios.get('/user/getMenuList?username=' + username,
                    {
                        headers: {
                            "Authorization": this.$store.getters.getToken
                        }
                    }
                ).then(response => {
                    const menuList = response.data.data;
                    this.menuList = menuList;
                    // this.$message({
                    //     type: 'success',
                    //     message: response.data.message
                    // });
                })
            },
            logout() {
                this.$confirm('确认退出?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '退出成功!'
                    });
                    this.$store.commit("REMOVE_INFO");
                    this.$router.replace("/login");
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消退出'
                    });
                });
            },
            saveActive(val){
                localStorage.setItem("active", val);
                this.active = val;
            }
        },
        data() {
            return {
                nickname: '',
                menuList: [],
                active: localStorage.getItem("active")
            }
        }
    }
</script>

<style lang="less" scoped>
    .index {
        padding: 0px;
        margin: 0px;
        height: 100%;
    }

    .el-container {
        height: 100%;
    }

    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;

        .el-menu {
            border-right: 0px;
            text-align: left;
        }
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
        /*text-align: center;*/
    }

    .nickname {
        position: absolute;
        right: 170px;
        text-align: right;
        color: cornsilk;
        cursor: pointer;
    }

    .logout {
        position: absolute;
        right: 10px;
        margin-top: 10px;
    }


</style>