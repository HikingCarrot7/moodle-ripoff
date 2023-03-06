import { useForm } from 'react-hook-form';

export const SubmissionForm = ({ submission, onSubmit }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <label htmlFor="description">Description</label>
      <input
        type="text"
        id="description"
        name="description"
        defaultValue={submission?.description}
        {...register('description', { required: false })}
      />

      <label htmlFor="file">File</label>
      <input
        type="file"
        id="file"
        name="file"
        {...register('file', { required: true })}
      />
      {errors.file && <p>A file is required</p>}

      <button type="submit">Submit</button>
    </form>
  );
};
