package com.csuf.expensesplittingapi.controller;

import com.csuf.expensesplittingapi.dto.GroupDto;
import com.csuf.expensesplittingapi.model.Group;
import com.csuf.expensesplittingapi.model.User;
import com.csuf.expensesplittingapi.repository.GroupRepository;
import com.csuf.expensesplittingapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    private Long getAuthenticatedUserId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto request) {
        Long userId = getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Group group = new Group(request.getName());
        group.getUsers().add(user); // Automatically add the creator to the group

        Group savedGroup = groupRepository.save(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GroupDto(savedGroup.getId(), savedGroup.getName()));
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getMyGroups() {
        Long userId = getAuthenticatedUserId();
        List<Group> groups = groupRepository.findByUsersId(userId);

        List<GroupDto> response = groups.stream()
                .map(g -> new GroupDto(g.getId(), g.getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}