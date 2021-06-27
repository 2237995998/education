import Vue from 'vue'
import VueRouter from 'vue-router'
import login from "../views/login";
import index from "../views/index";
import chooseCourse from "../views/choose-course";
import lookChooseResult from "../views/look-choose-result";
import historyCourse from "../views/look-history-course";
import thisStartCourse from "../views/look-this-start-course";
import courseStudentList from "../views/look-course-student-list"
import historyStartCourse from "../views/look-history-start-course"
import allStartCourse from "../views/look-all-start-course"
import addCourse from "../views/add-course"
import setTerm from "../views/set-term"
import openChoose from "../views/open-close-choose"
import lookThisGrade from "../views/look-this-grade"
import lookHistoryGrade from "../views/look-history-grade"
import insertGrade from "../views/add-grade"
import showStudentCourseGrade from "../views/look-student-course-grade"
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'index',
        component: index
    },
    {
        path: '/login',
        name: 'login',
        component: login
    },
    {
        path: '/showCourseStudent',
        name: 'courseStudentList',
        component: courseStudentList
    },
    {
        path: '/insertGrade',
        name: 'insertGrade',
        component: insertGrade
    }, {
        path: '/showStudentCourseGrade',
        name: 'showStudentCourseGrade',
        component: showStudentCourseGrade
    },
    {
        path: '/index',
        name: 'index',
        component: index,
        children: [
            {
                path: '/course/choose',
                name: 'chooseCourse',
                component: chooseCourse
            },{
                path: '/course/result',
                name: 'lookChooseResult',
                component: lookChooseResult
            }, {
                path: '/course/history',
                name: 'historyCourse',
                component: historyCourse
            }, {
                path: '/course_start/this',
                name: 'thisStartCourse',
                component: thisStartCourse
            }, {
                path: '/course_start/history',
                name: 'historyStartCourse',
                component: historyStartCourse
            }, {
                path: '/course_manage/look',
                name: 'allStartCourse',
                component: allStartCourse
            }, {
                path: '/course_manage/add',
                name: 'addCourse',
                component: addCourse
            }, {
                path: '/term/set',
                name: 'setTerm',
                component: setTerm
            }, {
                path: '/choose_manage/open',
                name: 'openChoose',
                component: openChoose
            }, {
                path: '/grade/this',
                name: 'lookThisGrade',
                component: lookThisGrade
            }, {
                path: '/grade/history',
                name: 'lookHistoryGrade',
                component: lookHistoryGrade
            }
        ]
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
