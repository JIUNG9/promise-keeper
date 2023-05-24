package com.studygroup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class GroupMemberPlanController {
//    /*
//    1. 그룹 맴버 플랜 생성
//    2. 그룹 맴버 플랜 삭제
//    3. 자신의 플랜 업데이트
//    4. 자신의 플랜 조회
//    5. 그룹 맴버의 플랜 조회
//    6. 자신의 플랜 완성률 조회
//     */
//
//    @PostMapping("/api/groups/{groupName}/members/{nickName}/plans")
//    public ResponseEntity<Object> createPlan(@PathVariable String groupName,
//                                             @PathVariable String nickName) {
//    }
//
//    @DeleteMapping("/api/groups/{groupName}/members/{nickName}/plan/{planId}")
//    public ResponseEntity<Object> deletePlan(@PathVariable String groupName,
//                                             @PathVariable String nickName,
//                                             @PathVariable Long planId) {
//    }
//
//    @PutMapping("/api/groups/{groupName}/members/{nickName}/plan/{planId}")
//    public ResponseEntity<Object> updatePlanDate(@PathVariable String groupName,
//                                             @PathVariable String nickName,
//                                             @PathVariable Long planId,
//                                                 @RequestBody UpdateDateForm updateDateForm) {
//
//    }
//
//    @PutMapping("/api/groups/{groupName}/members/{nickName}/plan/{planId}")
//    public ResponseEntity<Object> updatePlanTime(@PathVariable String groupName,
//                                                 @PathVariable String nickName,
//                                                 @PathVariable Long planId,
//                                                 @RequestBody UpdateTimeFrom updateTimeFrom ) {
//
//    }
//    @GetMapping("/api/groups/{groupName}/members/{nickName}/plan/{planId}")
//    public ResponseEntity<Object> findMyPlan(@PathVariable String groupName,
//                                             @PathVariable String nickName,
//                                             @PathVariable Long planId) {
//    }
//
//    @GetMapping("/api/groups/{groupName}/members/plans")
//    public ResponseEntity<Object> findGroupMembersPlans(@PathVariable String groupName,
//                                             @PathVariable String nickName,
//                                             @PathVariable Long planId) {
//    }
//
//
//
//    @GetMapping("/api/groups/{groupName}/members/{nickName}/plan/success-rate")
//    public ResponseEntity<Object> getMyPlanSuccessRate(@PathVariable String groupName,
//                                                       @PathVariable String nickName,
//                                                       @PathVariable Long planId) {
//
//    }
//
//    @PutMapping("/api/groups/{groupName}/members/{nickName}/plan/{planId}")
//    public ResponseEntity<Object> updateMyPlanStatus(@PathVariable String groupName,
//                                                 @PathVariable String nickName,
//                                                 @PathVariable Long planId,
//                                                 @RequestParam("plan-status") PlanStatus planStatus) {
//
//    }

}