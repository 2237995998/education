<template>
    <div class="outer">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="form-container">
            <el-form-item label="课程代码" prop="courseCode">
                <el-input v-model="ruleForm.courseCode"></el-input>
            </el-form-item>
            <el-form-item label="课程名称" prop="courseName">
                <el-input v-model="ruleForm.courseName"></el-input>
            </el-form-item>
            <el-form-item label="学分" prop="courseCredit">
                <el-input v-model="ruleForm.courseCredit"></el-input>
            </el-form-item>
            <el-form-item label="班号" prop="courseClass">
                <el-input v-model="ruleForm.courseClass"></el-input>
            </el-form-item>
            <el-form-item label="平时比例" prop="usualWeight" >
                <el-input v-model="ruleForm.usualWeight" placeholder="请输入小数"></el-input>
            </el-form-item>
            <el-form-item label="期末比例" prop="endWeight" >
                <el-input v-model="ruleForm.endWeight" placeholder="请输入小数"></el-input>
            </el-form-item>
            <el-form-item label="时间" prop="courseTime">
                <el-input v-model="ruleForm.courseTime" placeholder="例如: 1-17周 周一1-2节"></el-input>
            </el-form-item>
            <el-form-item label="地点" prop="coursePlace">
                <el-input v-model="ruleForm.coursePlace"></el-input>
            </el-form-item>
            <el-form-item label="课程类型" prop="courseType">
                <el-radio-group v-model="ruleForm.courseType">
                    <el-radio label="必修"></el-radio>
                    <el-radio label="选修"></el-radio>
                    <el-radio label="限选"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="学期" prop="courseTermId">
                <el-select v-model="ruleForm.courseTermId" filterable placeholder="请选择学期">
                    <el-option
                            v-for="item in courseTerm"
                            :key="item.termId"
                            :label="item.termName"
                            :value="item.termId">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="教师" prop="teacherId">
                <el-select v-model="ruleForm.teacherId" filterable placeholder="请选择教师">
                    <el-option
                            v-for="item in teachers"
                            :key="item.userId"
                            :label="item.nickname"
                            :value="item.userId">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即添加</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "add-course",
        data() {
            var timeVerify = (rule, value, callback) => {
                if (value) {
                    var reg = /^\d{1,2}-\d{1,2}[\u4e00-\u9fa5] [\u4e00-\u9fa5]{2}\d{1,2}-\d{1,2}[\u4e00-\u9fa5]$/;
                    if (!reg.test(value)) {
                        callback(new Error('请输入正确的格式,例如1-17周 周一1-2节'));
                    }
                    callback();
                }
            };
            var endWeightVerify = (rule, value, callback) => {
                if (value != 1 - this.ruleForm.usualWeight) {
                    callback(new Error('平时成绩比例加期末成绩比例不为1'));
                } else {
                    callback();
                }
            };
            return {
                teachers: [],
                courseTerm: [],
                ruleForm: {
                    courseCode: '',
                    courseName: '',
                    courseCredit: '',
                    courseClass: '',
                    usualWeight: 0.4,
                    endWeight: 0.6,
                    courseTime: '',
                    coursePlace: '',
                    courseType: '',
                    courseTermId: '',
                    teacherId: '',
                    schoolId: this.$store.getters.getUser.schoolId
                },
                rules: {
                    courseCode: [
                        {required: true, message: '请输入课程代码', trigger: 'blur'},
                    ],
                    courseName: [
                        {required: true, message: '请输入课程名称', trigger: 'blur'},
                    ],
                    courseCredit: [
                        {required: true, message: '请输入课程学分', trigger: 'blur'}
                    ],
                    courseClass: [
                        {required: true, message: '请输入上课班号', trigger: 'blur'}
                    ],
                    usualWeight: [
                        {required: true, message: '请输入平时成绩比例', trigger: 'blur'}
                    ],
                    endWeight: [
                        {required: true, message: '请输入期末成绩比例', trigger: 'blur'},
                        {validator: endWeightVerify, trigger: 'blur'}
                    ],
                    courseTime: [
                        {required: true, message: '请输入上课时间', trigger: 'blur'},
                        {validator: timeVerify, trigger: 'blur'}
                    ],
                    coursePlace: [
                        {required: true, message: '请输入上课地点', trigger: 'blur'}
                    ],
                    courseTermId: [
                        {required: true, message: '请选择上课学期', trigger: 'change'}
                    ],
                    teacherId: [
                        {required: true, message: '请选择教师', trigger: 'change'}
                    ],
                    courseType: [
                        {required: true, message: '请选择课程类型', trigger: 'change'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post("/course/add", this.ruleForm,
                            {
                                headers: {
                                    "Authorization": this.$store.getters.getToken
                                }
                            }).then(res => {
                            this.$message({
                                message: res.data.message,
                                type: "success"
                            });
                            this.resetForm('ruleForm');
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            getList() {
                const _this = this;
                this.$axios.get("/user/getTeachers?schoolId=" + _this.$store.getters.getUser.schoolId,
                    {
                        headers: {
                            "Authorization": _this.$store.getters.getToken
                        }
                    }
                ).then(response => {
                    var data = response.data.data;
                    _this.teachers = data;
                })
            },
            getTermList() {
                const _this = this;
                this.$axios.get("/term/getAll?notAll=1",
                    {
                        headers: {
                            "Authorization": _this.$store.getters.getToken
                        }
                    }
                ).then(response => {
                    var data = response.data.data;
                    _this.courseTerm = data.list;
                    _this.ruleForm.courseTermId = data.this;
                })
            },
        },

        created() {
            this.getList();
            this.getTermList();
        }

    }
</script>

<style scoped>

    .outer {

        background: #E9EEF3;
    }

    .form-container {
        width: 400px;
        margin: 0 auto;
        text-align: center;
    }
</style>