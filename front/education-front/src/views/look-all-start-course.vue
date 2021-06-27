<template>
    <div>
        <div class="top">
            <el-select v-model="value" placeholder="请选择学期">
                <el-option
                        v-for="item in options"
                        :key="item.termId"
                        :label="item.termName"
                        :value="item.termId">
                </el-option>
            </el-select>
            <el-button type="primary" @click.native="query()">查询</el-button>
        </div>
        <el-table
                class="table"
                :data="courseList"
                border
                stripe>

            <el-table-column
                    fixed="left"
                    width="50px"
                    label="序号"
                    align="center">
                <template slot-scope="scope">
                    {{(pageNum-1)*pageSize+scope.$index+1}}
                </template>
            </el-table-column>

            <el-table-column
                    width="110px"
                    sortable
                    fixed
                    prop="course.courseCode"
                    label="课程代码"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.course.courseCode}}</span>
                    <el-input type="text" v-model="scope.row.course.courseCode" v-else placeholder="请输入课程代码"></el-input>
                </template>
            </el-table-column>

            <el-table-column
                    sortable
                    width="150px"
                    prop="course.courseName"
                    label="课程名称">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.course.courseName}}</span>
                    <el-input type="text" v-model="scope.row.course.courseName" v-else placeholder="请输入课程名称"></el-input>
                </template>
            </el-table-column>

            <el-table-column
                    width="100px"
                    prop="course.courseType"
                    label="性质"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.course.courseType}}</span>
                    <el-select v-else v-model="scope.row.course.courseType" filterable>
                        <el-option label="必修" value="必修">
                        </el-option>
                        <el-option label="选修" value="选修">
                        </el-option>
                        <el-option label="限选" value="限选">
                        </el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column
                    width="80px"
                    label="学分"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.course.courseCredit}}</span>
                    <el-input type="text" v-model="scope.row.course.courseCredit" v-else placeholder="请输入学分"></el-input>
                </template>
            </el-table-column>
            <el-table-column
                    width="80px"
                    label="班号"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.course.courseClass}}</span>
                    <el-input type="text" v-model="scope.row.course.courseClass" v-else
                              placeholder="请输入教学班号"></el-input>
                </template>
            </el-table-column>
            <el-table-column
                    sortable
                    width="150px"
                    label="学院"
                    prop="school.schoolName"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.school.schoolName}}</span>
                    <el-input type="text" v-model="scope.row.school.schoolName" v-else placeholder="请输入学院名称"></el-input>
                </template>
            </el-table-column>
            <el-table-column
                    sortable
                    width="100px"
                    label="老师"
                    prop="teacher.nickname"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.teacher.nickname}}</span>
                    <el-select v-else v-model="scope.row.course.teacherId" filterable placeholder="请选择教师">
                        <el-option
                                v-for="item in teachers"
                                :key="item.userId"
                                :label="item.nickname"
                                :value="item.userId"
                        >
                        </el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column
                    width="220px"
                    label="学期"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.course.courseTerm}}</span>
                    <el-input type="text" v-model="scope.row.course.courseTerm" v-else placeholder="请输入学期"></el-input>
                </template>
            </el-table-column>
            <el-table-column
                    width="180px"
                    label="时间"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.course.courseTime}}</span>
                    <el-input type="text" v-model="scope.row.course.courseTime" v-else placeholder="请输入时间"></el-input>
                </template>
            </el-table-column>
            <el-table-column
                    width="100px"
                    label="地点"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.course.coursePlace}}</span>
                    <el-input type="text" v-model="scope.row.course.coursePlace" v-else placeholder="请输入地址"></el-input>
                </template>
            </el-table-column>
            <el-table-column
                    label="平时占比"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.course.usualWeight*100}}%</span>
                    <el-input type="text" v-model="scope.row.course.usualWeight" v-else
                              placeholder="请输入平时占比"></el-input>
                </template>
            </el-table-column>
            <el-table-column
                    label="期末占比"
                    align="center">
                <template slot-scope="scope">
                    <span v-if="!showEdit[scope.$index]">{{scope.row.course.endWeight*100}}%</span>
                    <el-input type="text" v-model="scope.row.course.endWeight" v-else placeholder="请输入期末占比"></el-input>
                </template>
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="150px"
                    align="center">
                <template slot-scope="scope">
                    <el-button v-if="!showEdit[scope.$index]" @click="showUpdate(scope.$index,scope.row)"
                               type="text" size="small">
                        <!--                        <i class="el-icon-edit"></i>-->
                        修改
                    </el-button>
                    <el-button v-if="showEdit[scope.$index]" @click="submitUpdate(scope.$index,scope.row)"
                               type="primary" round size="small">
                        确认
                    </el-button>
                    <el-button v-if="showEdit[scope.$index]" @click="cancelUpdate(scope.$index)"
                               type="primary" round size="small">
                        取消
                    </el-button>
                    <el-button v-if="!showEdit[scope.$index]" @click="showStudentList(scope.row.course.courseId)"
                               type="text" size="small">学生名单
                    </el-button>
                    <el-button v-if="!showEdit[scope.$index]" @click="deleteCourse(scope.row.course.courseId)"
                               type="text" size="small">删除
                    </el-button>
                    <br>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination class="page"
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="pageNum"
                       :page-sizes="[5, 10, 20, 30]"
                       :page-size="pageSize"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="total">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: "look-all-start-course",
        data() {
            return {
                teachers: [],
                showEdit: [],
                show: false,
                options: [],
                value: 0,
                courseList: [],
                pageSize: 5,
                total: 100,
                pageNum: 1,
            }
        },
        methods: {
            query() {
                this.getList();
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.getList();
            },
            handleCurrentChange(val) {
                this.pageNum = val;
                this.getList();
            },
            getList() {
                this.$axios.get("/course/showAllStart?pageNum=" + this.pageNum + '&pageSize=' + this.pageSize
                    + '&schoolId=' + this.$store.getters.getUser.schoolId + '&term=' + this.value,
                    {
                        headers: {
                            "Authorization": this.$store.getters.getToken
                        }
                    }
                ).then(response => {
                    var data = response.data.data;
                    this.courseList = data.list;
                    this.total = data.total;
                })
            },
            getTermList() {
                this.$axios.get("/term/getAll",
                    {
                        headers: {
                            "Authorization": this.$store.getters.getToken
                        }
                    }
                ).then(response => {
                    var data = response.data.data;
                    this.options = data.list;
                    this.value = data.this;
                    this.getList();
                })
            },
            getTeacherList() {
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
            showStudentList(val) {
                this.$store.commit("SET_COURSE_ID", val);
                var routeData = this.$router.resolve({path: '/showCourseStudent'});
                window.open(routeData.href);
            },
            deleteCourse(val) {
                this.$confirm('删除不可以恢复,确认要删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this = this;
                    this.$axios.delete("/course/delete?courseId=" + val,
                        {
                            headers: {
                                "Authorization": _this.$store.getters.getToken
                            }
                        }
                    ).then(response => {
                        var message = response.data.message;
                        this.$message({
                            message: message,
                            type: "success"
                        });
                        this.getList();
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },
            showUpdate(index) {
                this.showEdit[index] = true;
                this.$set(this.showEdit, index, true); //这里要用$set方法，否则页面状态不更新
            },
            //取消修改
            cancelUpdate(index) {
                this.$confirm("取消修改？", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.$set(this.showEdit, index, false);
                        this.getList();
                        this.getTermList();
                        this.getTeacherList();
                    })
                    .catch(() => {
                    });
            },
            submitUpdate(index, row) {
                this.$confirm("确认修改？", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.$axios.post("/course/update", row.course,
                            {
                                headers: {
                                    "Authorization": this.$store.getters.getToken
                                }
                            }).then(res => {
                            this.$message({
                                message: res.data.message,
                                type: "success"
                            })
                            this.showEdit[index] = false;
                            this.$set(this.showEdit, index, false);
                        })
                    })
                    .catch(() => {
                    });
            },
        }
        ,
        created() {
            this.getTermList();
            this.getTeacherList();
        }
    }
</script>

<style lang="less" scoped>

    .table {
        margin-top: 20px;
        text-align: center;
        border: 1px solid black;
        max-width: 1200px;
    }

    .top {
        margin: 0 auto;
        text-align: center;
    }

    .page {
        margin-top: 15px;
        text-align: center;
    }

</style>