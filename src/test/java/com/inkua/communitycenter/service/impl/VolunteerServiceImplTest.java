package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.Volunteer;
import com.inkua.communitycenter.exception.NotFoundException;
import com.inkua.communitycenter.repository.IVolunteerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VolunteerServiceImplTest {

    @Mock
    private IVolunteerRepository volunteerRepository;

    @InjectMocks
    private VolunteerServiceImpl volunteerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Volunteer> volunteers = new ArrayList<>();
        Mockito.when(volunteerRepository.findAll()).thenReturn(volunteers);

        List<Volunteer> result = volunteerService.findAll();
        assertEquals(volunteers, result);
    }

    @Test
    void testFindById() {
        Long volunteerId = 1L;
        Volunteer volunteer = new Volunteer();
        Mockito.when(volunteerRepository.findById(volunteerId)).thenReturn(Optional.of(volunteer));

        Volunteer result = volunteerService.findById(volunteerId);
        assertEquals(volunteer, result);
    }

    @Test
    void testCreateVolunteer() {
        Volunteer volunteer = new Volunteer();
        Mockito.when(volunteerRepository.save(volunteer)).thenReturn(volunteer);

        Volunteer result = volunteerService.createVolunteer(volunteer);
        assertEquals(volunteer, result);
    }

    @Test
    void testUpdateVolunteer() {
        Long volunteerId = 1L;
        Volunteer existingVolunteer = new Volunteer();
        Volunteer updatedVolunteer = new Volunteer();
        Mockito.when(volunteerRepository.findById(volunteerId)).thenReturn(Optional.of(existingVolunteer));
        Mockito.when(volunteerRepository.save(updatedVolunteer)).thenReturn(updatedVolunteer);

        Volunteer result = volunteerService.updateVolunteer(volunteerId, updatedVolunteer);
        assertEquals(updatedVolunteer, result);
    }

    @Test
    void testUpdateVolunteerNotFound() {
        Long volunteerId = 1L;
        Volunteer updatedVolunteer = new Volunteer();
        Mockito.when(volunteerRepository.findById(volunteerId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> volunteerService.updateVolunteer(volunteerId, updatedVolunteer));
        verify(volunteerRepository, never()).save(updatedVolunteer);
    }

    @Test
    void testDeleteVolunteer() {
        Long volunteerId = 1L;
        Volunteer existingVolunteer = new Volunteer();
        Mockito.when(volunteerRepository.findById(volunteerId)).thenReturn(Optional.of(existingVolunteer));

        Volunteer result = volunteerService.deleteVolunteer(volunteerId);
        assertEquals(existingVolunteer, result);
        verify(volunteerRepository, times(1)).deleteById(volunteerId);
    }

    @Test
    void testDeleteVolunteerNotFound() {
        Long volunteerId = 1L;
        Mockito.when(volunteerRepository.findById(volunteerId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> volunteerService.deleteVolunteer(volunteerId));
        verify(volunteerRepository, never()).deleteById(volunteerId);
    }
}
