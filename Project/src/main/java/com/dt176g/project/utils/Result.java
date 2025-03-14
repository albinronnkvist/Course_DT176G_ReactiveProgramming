package com.dt176g.project.utils;

/**
 * Represents a result of an operation, with success or failure.
 * 
 * @author Albin Rönnkvist
 */
public sealed interface Result<T, E> permits Result.Success, Result.Failure {

    /**
     * Returns a successful result with the given value.
     *
     * @param value The value of the successful result.
     * @return A successful result with the given value.
     */
    public static <T, E> Result<T, E> success(T value) {
        return new Success<>(value);
    }

    /**
     * Returns a failed result with the given error.
     * 
     * @param error The error of the failed result.
     * @return A failed result with the given error.
     */
    public static <T, E> Result<T, E> failure(E error) {
        return new Failure<>(error);
    }

    /**
     * Indicates whether the result is a success.
     * 
     * @return True if the result is a success, false otherwise.
     */
    public boolean isSuccess();

    /**
     * Indicates whether the result is a failure.
     * 
     * @return True if the result is a failure, false otherwise.
     */
    public boolean isFailure();

    /**
     * The value of a successful result.
     * 
     * @return The value of the successful result.
     */
    public T value();

    /**
     * The error of a failed result.
     * 
     * @return The error of the failed result.
     */
    public E error();

    /**
     * A record representing a successful result with a value.
     * 
     * @author Albin Rönnkvist
     */
    public record Success<T, E>(T value) implements Result<T, E> {
        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public T value() {
            return value;
        }

        @Override
        public E error() {
            throw new IllegalStateException("Cannot get error from Success");
        }
    }

    /**
     * A record representing a failed result with an error.
     * 
     * @author Albin Rönnkvist
     */
    public record Failure<T, E>(E error) implements Result<T, E> {
        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public T value() {
            throw new IllegalStateException("Cannot get value from Error");
        }

        @Override
        public E error() {
            return error;
        }
    }
}