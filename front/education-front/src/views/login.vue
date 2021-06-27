<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="login-container">
        <el-form-item label="账号" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input v-model="ruleForm.password" type="password"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
    </el-form>
</template>

<script>

    export default {
        name: "login",
        data() {
            return {
                ruleForm: {
                    username: '',
                    password: '',
                    // role: ''
                },
                rules: {
                    username: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 4, max: 25, message: '长度在 5 到 25 个字符', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const _this = this
                        this.$axios.post("/user/login", this.ruleForm).then(res => {
                            _this.$store.commit("SET_USERINFO", res.data.data.user)
                            _this.$store.commit("SET_TOKEN", res.data.data.token)
                            this.$router.push("/index")
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>
    .login-container {
        padding-top: 200px;
        width: 400px;
        margin: 0 auto;
        text-align: center;
    }

    .role {
        width: 300px;
    }
</style>